
--Carga Pozas
EXEC SP_A_tblPozas 'A','10','44','Recr�a','20'
EXEC SP_A_tblPozas 'B','10','44','Padrillo','20'
EXEC SP_A_tblPozas 'C','10','44','Recr�a','20'  -->Recr�a 
EXEC SP_A_tblPozas 'D','10','44','Recr�a','20'   
EXEC SP_A_tblPozas 'E','10','44','Engorde','20' -->Engorde

--Carga Cuyes
EXEC SP_A_tblCuyes 'C1','A','PD','Macho','10/10/20'
EXEC SP_A_tblCuyes 'C2','B','LC','Macho','09/20/20'
EXEC SP_A_tblCuyes 'C3','C','MM','Macho','10/30/19'
EXEC SP_A_tblCuyes 'C4','D','RC','Macho','01/30/20'
EXEC SP_A_tblCuyes 'C5','E','PD','Macho','03/15/20'

--Consultas
	--restricciones
		--Si lacatnte en poza de recr�a tiene 30d�as activar actividad por motivo de rotaci�n

			EXEC SP_C_CuyesEdadMax 'Recr�a',30

		--Madre adulta puede estar como maximo en alguna poza por 550 d�as  motivo para retiro del sistema
			EXEC SP_C_CuyesEdadMax 'Engorde',1

--A�adir atributo "Tipo e cuy"