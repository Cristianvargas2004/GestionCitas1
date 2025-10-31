-- 1. Insertar datos en tabla Usuario (Más usuarios para médicos)
INSERT INTO Usuario (Nombre, Email, Rol, Username, Password) VALUES
('Carlos Administrador', 'admin@clinica.com', 'Admin', 'admin', 'admin123'),
('Dr. Juan Pérez', 'juan.perez@clinica.com', 'Medico', 'dr.perez', 'medico123'),
('Dra. María García', 'maria.garcia@clinica.com', 'Medico', 'dra.garcia', 'medico123'),
('Dr. Pedro López', 'pedro.lopez@clinica.com', 'Medico', 'dr.lopez', 'medico123'),
('Dra. Ana Martínez', 'ana.martinez@clinica.com', 'Medico', 'dra.martinez', 'medico123'),
('Dr. Carlos Ruiz', 'carlos.ruiz@clinica.com', 'Medico', 'dr.ruiz', 'medico123'),
('Dra. Elena Vargas', 'elena.vargas@clinica.com', 'Medico', 'dra.vargas', 'medico123'),
('Dr. Miguel Torres', 'miguel.torres@clinica.com', 'Medico', 'dr.torres', 'medico123'),
('Dra. Patricia Flores', 'patricia.flores@clinica.com', 'Medico', 'dra.flores', 'medico123'),
('Dr. Rafael Herrera', 'rafael.herrera@clinica.com', 'Medico', 'dr.herrera', 'medico123'),
('Carlos Silva', 'carlos.silva@gmail.com', 'Paciente', 'carlos.s', 'paciente123'),
('María Rodríguez', 'maria.rodriguez@gmail.com', 'Paciente', 'maria.r', 'paciente123'),
('José González', 'jose.gonzalez@gmail.com', 'Paciente', 'jose.g', 'paciente123'),
('Sofía Hernández', 'sofia.hernandez@gmail.com', 'Paciente', 'sofia.h', 'paciente123'),
('Roberto Torres', 'roberto.torres@gmail.com', 'Paciente', 'roberto.t', 'paciente123');

-- 2. Insertar datos en tabla Medico (Cada médico con usuario_id único)
INSERT INTO Medico (Nombre, Especialidad, Telefono, Email, usuario_id) VALUES
('Dr. Juan Carlos Pérez', 'Cardiología', '987654321', 'juan.perez@clinica.com', 2),
('Dra. María Elena García', 'Pediatría', '987654322', 'maria.garcia@clinica.com', 3),
('Dr. Pedro José López', 'Neurología', '987654323', 'pedro.lopez@clinica.com', 4),
('Dra. Ana Sofía Martínez', 'Dermatología', '987654324', 'ana.martinez@clinica.com', 5),
('Dr. Carlos Alberto Ruiz', 'Traumatología', '987654325', 'carlos.ruiz@clinica.com', 6),
('Dra. Elena Cristina Vargas', 'Ginecología', '987654326', 'elena.vargas@clinica.com', 7),
('Dr. Miguel Ángel Torres', 'Oftalmología', '987654327', 'miguel.torres@clinica.com', 8),
('Dra. Patricia Lucía Flores', 'Psicología', '987654328', 'patricia.flores@clinica.com', 9),
('Dr. Rafael Antonio Herrera', 'Reumatología', '987654329', 'rafael.herrera@clinica.com', 10);

-- 3. Insertar datos en tabla Paciente
INSERT INTO Paciente (Nombre, Apellidos, DNI, Email, Telefono, Username, usuario_id) VALUES
('Carlos Eduardo', 'Silva Torres', '12345678', 'carlos.silva@gmail.com', '987123456', 'carlos.s', 11),
('María Isabel', 'Rodríguez López', '23456789', 'maria.rodriguez@gmail.com', '987123457', 'maria.r', 12),
('José Antonio', 'González Pérez', '34567890', 'jose.gonzalez@gmail.com', '987123458', 'jose.g', 13),
('Sofía Andrea', 'Hernández García', '45678901', 'sofia.hernandez@gmail.com', '987123459', 'sofia.h', 14),
('Roberto Carlos', 'Torres Martínez', '56789012', 'roberto.torres@gmail.com', '987123460', 'roberto.t', 15);

-- 4. Insert de Cita (CORREGIDO EL FORMATO DE HORA)
INSERT INTO Cita (Fecha, Hora, Observaciones, Estado, paciente_id, medico_id) VALUES
('2024-02-15', '09:00:00', 'Consulta general por dolor de cabeza', 'Confirmada', 1, 1),
('2024-02-16', '10:30:00', 'Seguimiento de tratamiento cardiaco', 'Pendiente', 2, 2),
('2024-02-17', '11:00:00', 'Consulta pediátrica rutinaria', 'Confirmada', 3, 3),
('2024-02-18', '14:00:00', 'Evaluación neurológica', 'Confirmada', 4, 4),
('2024-02-19', '15:30:00', 'Revisión dermatológica', 'Pendiente', 5, 5),
('2024-02-20', '08:00:00', 'Control traumatológico post-cirugía', 'Confirmada', 1, 6),
('2024-02-21', '12:00:00', 'Consulta ginecológica anual', 'Pendiente', 2, 7),
('2024-02-22', '16:00:00', 'Evaluación oftalmológica', 'Confirmada', 3, 8),
('2024-02-23', '09:30:00', 'Sesión de psicología', 'Cancelada', 4, 9),
('2024-02-24', '13:00:00', 'Control endocrinológico', 'Pendiente', 5, 1);

-- 5. Insert datos en tabla Diagnostico
INSERT INTO Diagnostico (Fecha, Diagnostico, Tratamiento, paciente_id, medico_id) VALUES
('2024-01-15', 'Cefalea tensional', 'Reposo, analgésicos suaves y técnicas de relajación', 1, 1),
('2024-01-20', 'Hipertensión arterial leve', 'Dieta baja en sodio, ejercicio moderado y medicación antihipertensiva', 2, 2),
('2024-01-25', 'Resfriado común', 'Reposo, hidratación abundante y antipiréticos si hay fiebre', 3, 3),
('2024-02-01', 'Depresión leve', 'Terapia cognitivo-conductual y seguimiento psicológico', 4, 4),
('2024-02-05', 'Dermatitis atópica', 'Cremas hidratantes y corticoides tópicos suaves', 5, 5),
('2024-02-10', 'Fractura de tobillo curada', 'Fisioterapia y rehabilitación progresiva', 1, 6),
('2024-02-12', 'Menopausia fisiológica', 'Tratamiento hormonal sustitutivo si es necesario', 2, 7),
('2024-02-14', 'Miopía leve', 'Uso de lentes correctoras y revisiones anuales', 3, 8),
('2024-02-15', 'Ansiedad generalizada', 'Terapia psicológica y técnicas de relajación', 4, 9),
('2024-02-18', 'Diabetes tipo 2', 'Dieta específica, ejercicio regular y medicación hipoglucemiante', 5, 1);