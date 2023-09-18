import React, { useMemo, useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import moment from 'moment';
import { Calendar, Views, DateLocalizer, momentLocalizer } from 'react-big-calendar';
import './styles.scss';

const mLocalizer = momentLocalizer(moment)

function Appointments({
  localizer = mLocalizer,
  events = [],
  weeklyWorkingSchedule = [],
  teacherId,
  updateIsUpdated,
}) {
  const [teacherEvents, setTeacherEvents] = useState(events);

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
        window.location.href = '/login';
      }
      
      return;
    }
  
    const userObj = JSON.parse(user);
    const userType = userObj.userType;
  
    if (userType !== 'STUDENT') {
      alert('No tienes permisos de estudiante');
      return;
    }

    const studentId = userObj.userTypeId;
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
          const startString = start.toString();
          const endString = end.toString();
          const originalStartDate = new Date(startString);
          const originalEndDate = new Date(endString);
          const formattedStartDate = new Date(originalStartDate.getTime() - (originalStartDate.getTimezoneOffset() * 60000)).toISOString();
          const formattedEndDate = new Date(originalEndDate.getTime() - (originalEndDate.getTimezoneOffset() * 60000)).toISOString();
          
          const data = {
            startsOn: formattedStartDate,
            endsOn: formattedEndDate,
            teacherId: teacherId,
            studentId: studentId
          };
  
          handleSubmit(data);
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

  const handleSubmit = async (data) => {
    const user = localStorage.getItem("user");
    const userObj = JSON.parse(user);
    const token = userObj.token;

    try {
      const response = await fetch('http://ec2-107-21-139-55.compute-1.amazonaws.com/v1/categories/1/appointments/', {
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
