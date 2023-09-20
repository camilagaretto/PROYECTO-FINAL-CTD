import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import moment from 'moment';
import Appointments from '../../../Components/Appointments';

const Summary = () => {
    const navigate = useNavigate();
    const params = useParams()
    const [teacherData, setTeacherData] = useState({});
    const [events, setEventsData] = useState([
        {
            startsOn: params.startDate || '',
            endsOn: params.endDate || '',
        },
    ]);
    const userObj = JSON.parse(localStorage.getItem('user')) || {};
    const studentId = userObj.userTypeId;
    const token = userObj.token;

    const formatearFechaHora = (fechaHora) => {
        return moment(fechaHora).format('DD/MM/YYYY HH:mm');
    };

    const startString = params.startDate.toString();
    const endString = params.endDate.toString();
    const originalStartDate = new Date(startString);
    const originalEndDate = new Date(endString);
    const formattedStartDate = new Date(originalStartDate.getTime() - (originalStartDate.getTimezoneOffset() * 60000)).toISOString();
    const formattedEndDate = new Date(originalEndDate.getTime() - (originalEndDate.getTimezoneOffset() * 60000)).toISOString();
    
    const fetchTeacherData = async () => {
        try {
        const response = await fetch(
            `http://localhost:8080/v1/categories/1/providers/${params.teacherId}`
        );
        const data = await response.json();
        setTeacherData(data);
        } catch (error) {
        console.error('Error al obtener los datos:', error);
        }
    };

    useEffect(() => {
        fetchTeacherData();
    }, [params.teacherId]);

    const handleSubmit = async () => {
        const data = {
            startsOn: formattedStartDate,
            endsOn: formattedEndDate,
            teacherId: teacherData.id,
            studentId: studentId
        };
    
        try {
          const response = await fetch('http://localhost:8080/v1/categories/1/appointments/', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(data),
          });
          if (response.ok) {
            alert('Turno guardado exitosamente');
            navigate('/confirmation')
          } else {
            alert('Error al crear turno');
          }
        } catch (error) {
          console.error('Error de red:', error);
        }
    };

    return (
        <main>
            <Container className='detail-container'>
                <section className='detail-left'>
                    <h2>Revisá si todo está bien</h2>
                    <Appointments events={events}/>
                    <h4>Tus datos</h4>
                    <form className="row g-3">
                        <div className="col-md-6">
                            <label htmlFor="firstName" className="form-label">Nombre</label>
                            <input
                                type="text"
                                className="form-control"
                                id="firstName"
                                name="firstName"
                                value={userObj.firstName}
                                disabled
                            />
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="lastName" className="form-label">Apellido</label>
                            <input
                                type="text"
                                className="form-control"
                                id="lastName"
                                name="lastName"
                                value={userObj.lastName}
                                disabled
                            />
                        </div>
                        <div className="col-md-12">
                            <label htmlFor="email" className="form-label">Email</label>
                            <input
                                type="text"
                                className="form-control"
                                id="email"
                                name="email"
                                value={userObj.email}
                                disabled
                            />
                        </div>
                    </form>
                </section>
                <section className='detail-right'>
                    <h4>Detalles</h4>
                    <div>
                        {teacherData.firstName} {teacherData.lastName}
                        <p>Profesor de </p>
                    </div>
                    <div>
                        Hora de clase
                        <p>Inicio: {formatearFechaHora(params.startDate)} </p>
                        <p>Fin: {formatearFechaHora(params.endDate)}</p>
                    </div>
                    <button className='btn btn-primary' onClick={handleSubmit}>Confirmar reserva</button>
                </section>
            </Container>
        </main>
    )
}

export default Summary