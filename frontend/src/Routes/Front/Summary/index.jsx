import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import moment from 'moment';
import Appointments from '../../../Components/Appointments';
import './styles.scss'

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
            `http://ec2-174-129-118-14.compute-1.amazonaws.com/v1/categories/1/providers/${params.teacherId}`
        );
        const data = await response.json();
        console.log(data)
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
          const response = await fetch('http://ec2-174-129-118-14.compute-1.amazonaws.com/v1/categories/1/appointments/', {
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
            <Container>
                <div className='row'>
                <h2>Revisá si todo está bien</h2>
                <section className='col-12 col-md-7'>
                    <div className='calendar-container'>
                        <Appointments events={events}/>
                        <h4 className='mt-3'>Tus datos</h4>
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
                    </div>
                </section>
                <section className='col-12 col-md-5'>
                    <div className='summary-container'>
                        <h4>Profesor {teacherData.firstName} {teacherData.lastName}</h4>
                        <img src={teacherData.profilePictureUrl} alt="Appkademy teacher image" />
                        <div>
                            <h5>Descripción</h5>
                            <p>{teacherData.shortDescription}</p>
                        </div>
                        <div>
                            <h5>Hora de clase</h5>
                            <p><b>Inicio:</b> {formatearFechaHora(params.startDate)} </p>
                            <p><b>Fin:</b> {formatearFechaHora(params.endDate)}</p>
                        </div>
                    </div>
                    <button className='btn btn-primary btn-lg w-100 mt-4' onClick={handleSubmit}>Confirmar reserva</button>
                </section>
                </div>
            </Container>
        </main>
    )
}

export default Summary