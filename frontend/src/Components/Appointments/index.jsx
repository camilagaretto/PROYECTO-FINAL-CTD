import React, { useMemo, useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import moment from 'moment';
import { useNavigate } from 'react-router-dom';
import { Calendar, Views, DateLocalizer, momentLocalizer } from 'react-big-calendar';
import './styles.scss';

const mLocalizer = momentLocalizer(moment)

function Appointments({
  localizer = mLocalizer,
  events = [],
  weeklyWorkingSchedule = [],
  teacherId = '',
}) {
  const [teacherEvents, setTeacherEvents] = useState(events);
  const navigate = useNavigate();

  useEffect(() => {
    const formattedEvents = events.map(event => ({
      start: moment(event.startsOn).toDate(),
      end: moment(event.endsOn).toDate(),
      title: 'Reservado',
    }));
    setTeacherEvents(formattedEvents)
  }, [events]);

  const minTime = new Date();
  minTime.setHours(8, 0, 0);
  const maxTime = new Date();
  maxTime.setHours(23, 0, 0);

  const { views } = useMemo(
    () => ({
      views: {
        week: true,
      },
    }),[]
  );

  const handleSelectEvent = (event) => {
    window.alert(event.title);
  };

  const handleSelectSlot = ({ start, end }) => {
    const user = localStorage.getItem("user");
  
    if (!user) {
      const isConfirmed = window.confirm('Debes iniciar sesión para reservar. ¿Deseas ir a la página de inicio de sesión?');
      
      if (isConfirmed) {
        navigate('/');
      }
      
      return;
    }
    const userObj = JSON.parse(user);
    const userType = userObj.userType;
  
    if (userType !== 'STUDENT') {
      alert('No tienes permisos de estudiante');
      return;
    }

    const currentDate = new Date();
    const dayOfWeek = start.getDay();
    const startHour = start.getHours();
    const endHour = end.getHours();
    const dayNames = ["sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"];
    const currentDay = dayNames[dayOfWeek].toLowerCase();

    if (start > currentDate && weeklyWorkingSchedule[currentDay]) {
      const checkInHours = parseInt(weeklyWorkingSchedule.checkIn.split(":")[0]);
      const checkOutHours = parseInt(weeklyWorkingSchedule.checkOut.split(":")[0]);
  
      if (startHour >= checkInHours && endHour <= checkOutHours) {
        const isConfirmed = window.confirm('¿Seguro que quieres reservar en este horario?');
  
        if (isConfirmed) {          
          navigate(`/summary/${teacherId}/${start}/${end}`);
        }
      }
    } else {
      console.log('Seleccionaste un día no válido:', start, end);
    }
  };

  const slotPropGetter = (date) => {
    const currentDate = new Date();
    
    if (date < currentDate) {
      return {};
    }
    
    const hours = date.getHours();
    const dayOfWeek = date.getDay();
  
    const dayNames = ["sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"];
    const currentDay = dayNames[dayOfWeek].toLowerCase();
  
    if (weeklyWorkingSchedule[currentDay]) {
      const checkInHours = parseInt(weeklyWorkingSchedule.checkIn.split(":")[0]);
      const checkOutHours = parseInt(weeklyWorkingSchedule.checkOut.split(":")[0]);
      if (hours >= checkInHours && hours < checkOutHours) {
        return {
          style: {
            backgroundColor: 'green',
          },
        };
      }
    }
    return {};
  };

<<<<<<< HEAD
  const handleSubmit = async (data) => {
    const user = localStorage.getItem("user");
    const userObj = JSON.parse(user);
    const token = userObj.token;

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
        updateIsUpdated()
      } else {
        alert('Error al crear turno');
      }
    } catch (error) {
      console.error('Error de red:', error);
    }
  };

=======
>>>>>>> 1c7beb6cd5e214e8b99027620fb6c766990f5fe7
  return (
    <div className="calendarAppointment">
      <Calendar
        defaultView={Views.WEEK}
        events={teacherEvents}
        localizer={localizer}
        step={60}
        timeslots={1}
        views={views}
        onSelectEvent={handleSelectEvent}
        onSelectSlot={handleSelectSlot}
        selectable
        min={minTime}
        max={maxTime}
        slotPropGetter={slotPropGetter}
      />
    </div>
  );
}

Appointments.propTypes = {
  localizer: PropTypes.instanceOf(DateLocalizer),
  showDemoLink: PropTypes.bool,
  scrollToTime: PropTypes.instanceOf(Date),
};

export default Appointments;
