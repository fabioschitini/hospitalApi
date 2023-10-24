INSERT INTO DATAS(ano, mes,dia_da_semana,dia,hora,minuto) VALUES('2023','Outubro', 'Segunda',20,10,30);
INSERT INTO DATAS(ano, mes,dia_da_semana,dia,hora,minuto) VALUES('2023','Outubro', 'Terça',21,11,30);
INSERT INTO DATAS(ano, mes,dia_da_semana,dia,hora,minuto) VALUES('2023','Outubro', 'Quarta',22,12,30);

INSERT INTO CONSULTAS(data_id, cancelado, medico,paciente,motivo) VALUES(1,false, 1,1,'PacienteDesistiu');
INSERT INTO CONSULTAS(data_id, cancelado, medico,paciente,motivo) VALUES(2,false, 1,2,'MedicoCancelou');
INSERT INTO CONSULTAS(data_id, cancelado, medico,paciente,motivo) VALUES(3,false, 2,1,'Outros');