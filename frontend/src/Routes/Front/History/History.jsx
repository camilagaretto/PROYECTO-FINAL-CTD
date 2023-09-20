import React, { useEffect, useState } from 'react'
import './History.scss'
import { useParams } from 'react-router-dom'

const History = () => {

    const {id} = useParams()
    const [appointmentsData, setAppointmentsData] = useState([])

    const getStudentData = async (token) => {
        const response = await fetch(`http://localhost:8080/v1/categories/1/customers/${id}`, {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        })
        if(response.ok){
            const data = await response.json();
            const appointmentsWithTeachers = await Promise.all(data.scheduledAppointments.map(async (appointment) => {
                const teacherData = await getTeacherData(appointment.teacherId);
                return { appointment, teacherData };
            }));
            setAppointmentsData(appointmentsWithTeachers);
        }
    }

    const getTeacherData = async (teacherId) => {
        const response  = await fetch(`http://localhost:8080/v1/categories/1/providers/${teacherId}`, {
            method: 'GET',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
            }
        })
        if(response.ok){
            const data = await response.json();
            return data;
        }
        return null;
    }

    useEffect(() => {
        const userDataJSON = localStorage.getItem('user');
        if (userDataJSON) {
            const userData = JSON.parse(userDataJSON);
            const { token } = userData;
            getStudentData(token);
        }
    }, [id])

    return (
        <main id='history'>
            <h1>Historial de reservas</h1>
            {
                appointmentsData.map(({ appointment, teacherData }) => (
                    <div key={appointment.id} className='appointment-card'>
                        <img src={teacherData.profilePictureUrl} alt="Appkademy teacher" />
                        <div className='appointment-text-box'>
                            <strong>Profesor</strong>
                            <p>{teacherData.firstName}</p>
                        </div>
                        <div>
                            <strong>Inicio</strong>
                            <p>{appointment.startsOn}</p>
                        </div>
                        <div>
                            <strong>Fin</strong>
                            <p>{appointment.endsOn}</p>
                        </div>
                    </div>
                ))
            }
        </main>
    )
}

export default History