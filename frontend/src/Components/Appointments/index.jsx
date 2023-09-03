import React, { useMemo, useState, useCallback, useEffect} from 'react';
import PropTypes from 'prop-types';
import moment from 'moment';
import events from '../../constants/events';
import { Calendar, Views, DateLocalizer, momentLocalizer} from 'react-big-calendar';
import './styles.scss';

const mLocalizer = momentLocalizer(moment);

function Appointments({
  localizer = mLocalizer, 
  // events,
  weeklyWorkingSchedule
}) {
  const [myEvents, setEvents] = useState(events);
  useEffect(() => {
    const formattedEvents = events.map(event => ({
      start: moment(event.startsOn).toDate(),
      end: moment(event.endsOn).toDate(),
      title: 'No disponible',
    }));

    setEvents(formattedEvents);
  }, [events]);

  const minTime = new Date();
  minTime.setHours(8, 0, 0);
  const maxTime = new Date();
  maxTime.setHours(23, 0, 0);

  const {defaultDate, views } = useMemo(
    () => ({
      defaultDate: new Date(),
      views: {
        week: true,
      },
    }),
    []
  );

  const handleSelectEvent = useCallback(
    (event) => window.alert(event.title), []
  );

  const handleSelectSlot = useCallback(
    ({ start, end }) => {
      const dayOfWeek = start.getDay();
      const startHour = start.getHours();
      const endHour = end.getHours();
  
      // Obtener el nombre del día actual en inglés (lowercase)
      const dayNames = ["sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"];
      const currentDay = dayNames[dayOfWeek].toLowerCase();
  
      // Verificar si el día actual está habilitado en el weeklyWorkingSchedule
      if (weeklyWorkingSchedule && weeklyWorkingSchedule[currentDay]) {
        // Obtener las horas de checkIn y checkOut para el día actual
        const checkInHours = parseInt(weeklyWorkingSchedule.checkIn.split(":")[0]);
        const checkOutHours = parseInt(weeklyWorkingSchedule.checkOut.split(":")[0]);
        
        // Verificar si el rango de tiempo seleccionado está dentro del horario válido
        if (startHour >= checkInHours && endHour <= checkOutHours) {
          const title = window.prompt('Reservar');
          if (title) {
            setEvents((prev) => [...prev, { start, end, title }]);
          }
        } else {
          console.log('Seleccionaste un rango de tiempo no válido:', start, end);
        }
      } else {
        console.log('Seleccionaste un día no válido:', start, end);
      }
    },
    [setEvents, weeklyWorkingSchedule]
  );

  const slotPropGetter = (date) => {
    const hours = date.getHours();
    const dayOfWeek = date.getDay();

    const dayNames = ["sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"];
    const currentDay = dayNames[dayOfWeek].toLowerCase();

    if (weeklyWorkingSchedule && weeklyWorkingSchedule[currentDay]) {
      const checkInHours = parseInt(weeklyWorkingSchedule.checkIn.split(":")[0]);
      const checkOutHours = parseInt(weeklyWorkingSchedule.checkOut.split(":")[0]);
      if (hours >= checkInHours && hours < checkOutHours) {
        return {
          style: {
            backgroundColor: 'lightblue',
          },
        };
      }
    }
    return {};
  };

  return (
    <div className="calendarAppointment">
      <Calendar
        defaultDate={defaultDate}
        defaultView={Views.WEEK}
        events={myEvents}
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
