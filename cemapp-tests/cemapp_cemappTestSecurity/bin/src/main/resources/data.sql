/* PROCESOS */
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(1,'P.C. 01. ACCIÓN TUTORIAL.','CLAVE',1);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(2,'P.C. 02. ORIENTACIÓN Y ACOMPAÑAMIENTO DE ALUMNOS','CLAVE',2);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(3,'P.C. 04. ATENCIÓN A LA DIVERSIDAD','CLAVE',3);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(4,'P.C. 05. ACCIÓN DOCENTE (PROC. EDUC. EN EL AULA)','CLAVE',4);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(5,'P.C. 07. GESTIÓN DE CONFLICTOS DISCIPLINARES. (de la convivencia)','CLAVE',5);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(6,'P.C. 09. ADMISIÓN, MATRICULACIÓN Y ACOGIDA DE ALUMNOS','CLAVE',6);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(7,'P.C. 10. ACCIÓN PASTORAL ESCOLAR','CLAVE',7);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(8,'P.C. 12. IMAGEN DEL CENTRO','CLAVE',8);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(9,'P.C. 14. FORMACIÓN EN CENTROS DE TRABAJO.','CLAVE',9);
INSERT INTO PROCESO (ID,NOMBRE,TIPO,PESO) VALUES(10,'P.E. 06. MEJORA CONTINUA.','ESTRATEGICO',10);


/* INDICADORES */

INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(1,'IND 01.1 % Familias diferentes que se entrevistan con el tutor',1,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(2,'IND 01.2 % alumnos diferentes entrevistados por el tutor',1,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(3,'IND 01.3 Nº total de entrevistas realizadas por el tutor con alumnos',1,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(4,'IND 01.4 Nº total de entrevistas realizadas por el tutor con famillias',1,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(5,'IND 01.5 % de acciones del Plan de Acción Tutorial realizadas sobre las planificadas.',1,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(6,'IND 01.6 % de acciones del Plan de Acción Tutorial eficaces sobre las realizadas.',1,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(7,'IND 01.7 % Padres que asisten a las reuniones de tutoría grupal.',1,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(8,'IND 01.8 Grado de satisfacción de los alumnos con la acción tutorial',1,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(9,'IND 01.9 Grado de satisfacción de las familias con la acción tutorial',1,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(10,'IND 01.10 Grado de satisfacción de los profesores con la acción tutorial',1,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(11,'IND 01.11 Grado satisfacción profesores con las acciones tutoriales desarrolladas con los alumnos',1,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(12,'IND 02.1 % de pruebas colectivas realizadas sobre las planificadas.',2,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(13,'IND 02.2 % de acciones de Orientación profesional–vocacional realizadas sobre las planificadas',2,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(14,'IND 02.3 Grado de satisfacción de los alumnos con la orientación',2,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(15,'IND 02.4 Grado de Satisfacción de Familias con la Orientación',2,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(16,'IND 02.5 Grado de Satisfacción de los profesores con la Orientación',2,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(17,'IND 04.1 Porcentaje áreas-asignaturas-materias aprobadas de todo el alumnado con Adaptación Curricular No Significativa',3,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(18,'IND 04.2 Porcentaje áreas-asignaturas-materias aprobadas de todo el alumnado con Adaptación Curricular Significativa',3,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(19,'IND 04.3 Porcentaje áreas-asignaturas-materias aprobadas de todo el alumnado con refuerzo o apoyo.',3,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(20,'IND 04.4 Porcentaje otros programas valorados como eficaces.',3,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(21,'IND 04.5 % materias pendientes recuperadas (EP, ESO, Bach y FPB)',3,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(22,'IND 04.6 % alumnos repetidores que promocionan sin imperativo legal',3,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(23,'IND 04.7 Grado de satisfacción de los alumnos con las medidas de atención a la diversidad',3,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(24,'IND 04.8 Grado de satisfacción de las familias con las medidas de atención a la diversidad',3,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(25,'IND 04.9 Grado de satisfacción profesores con la Atención a la Diversidad',3,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(26,'IND 05.1 % alumnos que en este momento promocionarían de curso o titularían (imperativo legal)',4,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(27,'IND 05.2 % alumnos que en este momento promocionarían (basado en el nº real de suspensos)',4,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(28,'IND 05.3 % de alumnos sin suspensos',4,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(29,'IND 05.4 % alumnos con 3 o más suspensos',4,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(30,'IND 05.5 Porcentaje de alumnos que aprueban la PAU en Junio con respecto a los matriculados en 2º BACH',4,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(31,'IND 05.6 Nota media alumnos PAU (1ª convocatoria)',4,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(32,'IND 05.7 Diferencia entre media PAU y media Bachillerato (1ª convocatoria)',4,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(33,'IND 05.8 % de actividades complementarias programadas sobre las realizadas.',4,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(34,'IND 05.9 Grado de satisfacción de los alumnos con la acción docente',4,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(35,'IND 05.10 Grado de satisfacción de las familias con la acción docente',4,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(36,'IND 05.11 Grado de satisfacción de los profesores con la acción docente',4,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(37,'IND 05.12 Grado de satisfacción de los alumnos con las actividades complementarias',4,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(38,'IND 05.13 Grado de satisfacción del profesorado con las actividades complementarias',4,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(39,'IND 07.1 Nº de alumnos diferentes suspendidos del derecho de asistencia al centro',5,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(40,'IND 07.2 % de alumnos diferentes suspendidos del derecho de asistencia al centro',5,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(41,'IND 07.3 % alumnos diferentes con ausencias injustificadas en 2 o más días (en el mismo trimestre).',5,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(42,'IND 07.4 % alumnos diferentes con impuntualidades injustificadas (Se contabilizará a partir de 3 impuntualidades en el mismo trimestre)',5,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(43,'IND 07.5 Grado de satisfacción de los alumnos con la Gestión de Conflictos',5,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(44,'IND 07.6 Grado de satisfacción de las Familias con la Gestión de Conflictos',5,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(45,'IND 07.7 Grado de satisfacción del profesorado con la Gestión de Conflictos',5,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(46,'IND 09.1 % de ocupación (n.º alumnos/ratio)',6,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(47,'IND 09.2 Porcentaje de solicitudes recibidas como primera opción respecto a plazas ofertadas',6,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(48,'IND 09.3 Nº solicitudes recibidas (por curso)',6,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(49,'IND 09.4 Nº de alumnos que se dan de baja ',6,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(50,'IND 09.5 Grado de satisfacción de alumnos de nuevo ingreso con la Admisión, Matriculación y Acogida de alumnos',6,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(51,'IND 09.6 Grado de satisfacción de las Familias de nuevo ingreso con la Admisión, Matriculación y Acogida de alumnos',6,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(52,'IND 10.1 % alumnos que participan en actividades voluntarias de pastoral escolar (celebraciones, retiros, convivencias, …)',7,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(53,'IND 10.2 Grado de satisfacción de los alumnos con la Pastoral',7,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(54,'IND 10.3 Grado de satisfacción de las Familias con la Pastoral',7,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(55,'IND 10.4 Grado de satisfacción del profesorado con la Pastoral',7,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(56,'IND 12.1 Nº Reconocimientos / premios concedidos por parte de entidades externas ',8,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(57,'IND 12.2 Nº de ocasiones en los que el centro aparece en los medios de comunicación (medios externos a Salesianos)',8,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(58,'IND 12.3 Número de actividades en las que el Centro colabora con otras instituciones externas',8,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(59,'IND 12.4 Grado de satisfacción de los alumnos con la Imagen del Centro',8,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(60,'IND 12.5 Grado de satisfacción de las Familias con la Imagen del Centro',8,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(61,'IND 12.6 Grado de satisfacción del profesorado con la Imagen del Centro',8,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(62,'IND 12.7 Grado de satisfacción de los padres con la Plataforma "QE Escuela-Familia"',8,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(63,'IND 14.1 % de alumnos matriculados en segundo curso que realizan el módulo de FCT',9,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(64,'IND 14.2 % de alumnos que aprueban con apto el módulo de FCT sobre los que han realizado la FCT.',9,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(65,'IND 14.3 % de alumnos contratados en empresas tras haber terminado la FCT',9,TRUE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(66,'IND 14.4 Grado de satisfaccion de los alumnos con la FCT',9,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(67,'IND 14.5 Grado de satisfacción de las empresas con la FCT',9,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(68,'IND 06.1 E Mejora en indicadores medición objetiva definida en el PSM ',10,FALSE);
INSERT INTO INDICADOR (ID,NOMBRE,PROCESO_ID,ES_PORCENTAJE) VALUES(69,'IND 06.2 E Mejora en indicadores de la satisfacción del cliente',10,FALSE);

/* INDICACORES COLEGIO */

INSERT INTO INDICADOR_CENTRO (ID) VALUES (56);
INSERT INTO INDICADOR_CENTRO (ID) VALUES (57);
INSERT INTO INDICADOR_CENTRO (ID) VALUES (58);
INSERT INTO INDICADOR_CENTRO (ID) VALUES (68);
INSERT INTO INDICADOR_CENTRO (ID) VALUES (69);

