CREATE DATABASE hospital;

USE hospital;

CREATE TABLE medicamentos(
    id INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100),
    fabricante VARCHAR(70),
    categoria VARCHAR(50),
    descricao VARCHAR(100),
    quantidade INT,
    validade DATE,
    PRIMARY KEY (id)
);

INSERT INTO medicamentos (
    id,
    nome,
    fabricante,
    categoria,
    descricao,
    quantidade,
    validade
)
VALUES
    (1, 'Paracetamol', 'EMS', 'Analgésico', 'Medicamento utilizado para alívio da febre e dores leves a moderadas.', 500, '2025-12-31'),
    (2, 'Amoxicilina', 'Medley', 'Antibiótico', 'Antibiótico usado no tratamento de infecções bacterianas.', 300, '2026-06-15'),
    (3, 'Omeprazol', 'Eurofarma', 'Antiácido', 'Medicamento utilizado para reduzir a produção de ácido no estômago.', 200, '2025-09-30');