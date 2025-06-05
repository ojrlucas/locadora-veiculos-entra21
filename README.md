# 🚗 Locadora de Veículos

Sistema de gerenciamento de uma locadora de veículos, desenvolvido em **Java** com conexão a banco de dados **MySQL** via **JDBC**. A aplicação permite o cadastro de organizações, clientes, veículos e controle dos aluguéis, com cálculo automático de valores conforme os dias utilizados e possíveis atrasos.

---

## 📌 Funcionalidades

- Cadastro de organizações (empresas que alugam veículos)
- Cadastro de clientes
- Cadastro de veículos
- Registro de aluguéis de veículos
- Cálculo do valor total de aluguéis com base nos dias e taxas de atraso
- Integração com banco de dados MySQL
- Separação em camadas: Entidades, DAO, Conexão, Main

---
## 🗃️ Banco de Dados

```sql
CREATE DATABASE db_locadora;

CREATE TABLE tb_clientes (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    fone VARCHAR(14) NOT NULL
);

CREATE TABLE tb_veiculos (
	id INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    ano CHAR(4) NOT NULL,
    precoDiaria DECIMAL(8,2) NOT NULL,    
    tipo ENUM('CARRO','CAMINHAO','MOTO') NOT NULL,
	situacao ENUM('DISPONIVEL','INDISPONIVEL','INATIVO') NOT NULL
);

CREATE TABLE tb_locacoes (
	id INT PRIMARY KEY AUTO_INCREMENT,
    qtd_dias INT NOT NULL,
    veiculo_id INT NOT NULL,
    cliente_id INT NOT NULL,
    data_locacao DATE NOT NULL,
    data_devolucao DATE,
    data_expiracao DATE NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES tb_clientes(id),
    FOREIGN KEY (veiculo_id) REFERENCES tb_veiculos(id)
);

DELIMITER //

CREATE PROCEDURE sp_insert_locacoes (
    IN p_qtd_dias INT,
    IN p_veiculo_id INT,
    IN p_cliente_id INT,
    IN p_data_locacao DATE,
    IN p_data_expiracao DATE,
    IN p_data_devolucao DATE
)
BEGIN
    DECLARE v_situacao VARCHAR(20);
    SELECT situacao INTO v_situacao FROM tb_veiculos WHERE id = p_veiculo_id;

    IF v_situacao = 'INDISPONIVEL' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Veículo está indisponível para locação.';
    ELSEIF v_situacao = 'INATIVO' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Veículo está inativo e não pode ser locado.';
    END IF;
    
    INSERT INTO tb_locacoes (
        qtd_dias,
        veiculo_id,
        cliente_id,
        data_locacao,
        data_expiracao,
        data_devolucao
    ) VALUES (
        p_qtd_dias,
        p_veiculo_id,
        p_cliente_id,
        p_data_locacao,
        p_data_expiracao,
        p_data_devolucao
    );
    
    UPDATE tb_veiculos 
    SET situacao = 'INDISPONIVEL' 
    WHERE id = p_veiculo_id;
    
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_registrar_devolucao (
    IN l_id INT,
    IN l_dataDevolucao DATE,
    IN l_qtdDias INT
)
BEGIN
    DECLARE v_veiculo_id VARCHAR(20);
    SELECT veiculo_id INTO v_veiculo_id FROM tb_locacoes WHERE id = l_id;
	
    UPDATE tb_locacoes SET data_devolucao = l_dataDevolucao, qtd_dias = l_qtdDias WHERE id = l_id;
    UPDATE tb_veiculos SET situacao = 'DISPONIVEL' WHERE id = v_veiculo_id;    
END //

DELIMITER ;
