import React, { useState } from 'react';
import './TeacherForm.scss'

function TeacherForm() {
    const [userData, setUserData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        shortDescription: '',
        fullDescription: '',
        address: {
          country: '',
          province: '',
          city: '',
          streetName: '',
          streetNumber: '',
          floorApt: '',
        },
        hourlyRates: {
          ARS: '',
          USD: '',
        },
        modalities: {
          FACE_TO_FACE: false,
          REMOTE: false,
        },
        proficiencies: [
          {
            masteryLevel: '',
            subject: '',
          },
        ],
        weeklyWorkingSchedule: {
          checkIn: '',
          checkOut: '',
          sunday: false,
          monday: false,
          tuesday: false,
          wednesday: false,
          thursday: false,
          friday: false,
          saturday: false,
        },
        scheduledAppointments: [],
        profilePictureUrl: '',
    });
    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setUserData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };
    const handleAddressChange = (event) => {
        const { name, value } = event.target;
        setUserData((prevData) => ({
          ...prevData,
          address: {
            ...prevData.address,
            [name]: value,
          },
        }));
    };
    const handleHourlyRatesChange = (event) => {
         const { name, value } = event.target;
        setUserData((prevData) => ({
            ...prevData,
            hourlyRates: {
            ...prevData.hourlyRates,
            [name]: value,
            },
        }));
    };
    const handleModalitiesChange = (event) => {
        const { name, checked } = event.target;
        setUserData((prevData) => ({
          ...prevData,
          modalities: {
            ...prevData.modalities,
            [name]: checked,
          },
        }));
    };
    const handleProficiencyChange = (field, value) => {
        setUserData((prevData) => {
          const updatedProficiencies = [...prevData.proficiencies];
          updatedProficiencies[0][field] = value;
          return {
            ...prevData,
            proficiencies: updatedProficiencies,
          };
        });
    };
    const handleWeeklyScheduleChange = (event) => {
        const { name, value, type, checked } = event.target;
        const newValue = type === 'checkbox' ? checked : value;
        setUserData((prevData) => ({
          ...prevData,
          weeklyWorkingSchedule: {
            ...prevData.weeklyWorkingSchedule,
            [name]: newValue,
          },
        }));
    };
    const handleSuccessfulSubmit = () => {
        setUserData({
          firstName: '',
          lastName: '',
          email: '',
          shortDescription: '',
          fullDescription: '',
          address: {
            country: '',
            province: '',
            city: '',
            streetName: '',
            streetNumber: '',
            floorApt: '',
          },
          hourlyRates: {
            ARS: '',
            USD: '',
          },
          modalities: {
            FACE_TO_FACE: false,
            REMOTE: false,
          },
          proficiencies: [
            {
              masteryLevel: '',
              subject: '',
            },
          ],
          weeklyWorkingSchedule: {
            checkIn: '',
            checkOut: '',
            sunday: false,
            monday: false,
            tuesday: false,
            wednesday: false,
            thursday: false,
            friday: false,
            saturday: false,
          },
          scheduledAppointments: [],
          profilePictureUrl: '',
        });
    };
    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/v1/categories/1/providers/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
            });
            if (response.ok) {
                alert('Usuario creado exitosamente');
            } else {
                alert('Error al crear usuario');
            }
            handleSuccessfulSubmit()
        } catch (error) {
            console.error('Error de red:', error);
        }
    };

  return (
    <div className="formAdd__container">
        <h1 className='mb-5'>Alta de profesor</h1>
        <form className="row g-3" onSubmit={handleSubmit}>
            <div className="col-md-6">
            <label htmlFor="firstName" className="form-label">Nombre</label>
            <input
                type="text"
                className="form-control"
                id="firstName"
                name="firstName"
                value={userData.firstName}
                onChange={handleInputChange}
            />
            </div>
            <div className="col-md-6">
            <label htmlFor="lastName" className="form-label">Apellido</label>
            <input
                type="text"
                className="form-control"
                id="lastName"
                name="lastName"
                value={userData.lastName}
                onChange={handleInputChange}
            />
            </div>
            <div className="col-12">
                <label htmlFor="email" className="form-label">Correo</label>
                <input
                    type="email"
                    className="form-control"
                    id="email"
                    name="email"
                    value={userData.email}
                    onChange={handleInputChange}
                />
            </div>
            <div className="col-12">
                <label htmlFor="shortDescription" className="form-label">Título</label>
                <input
                    type="text"
                    className="form-control"
                    id="shortDescription"
                    name="shortDescription"
                    value={userData.shortDescription}
                    onChange={handleInputChange}
                />
            </div>
            <div className="col-12">
                <label htmlFor="fullDescription" className="form-label">Descripción</label>
                <textarea
                    className="form-control"
                    id="fullDescription"
                    name="fullDescription"
                    value={userData.fullDescription}
                    onChange={handleInputChange}
                />
            </div>
            <div className="col-md-6">
                <label htmlFor="country" className="form-label">País</label>
                <select
                    className="form-select"
                    id="country"
                    name="country"
                    value={userData.address.country}
                    onChange={handleAddressChange}
                    >
                    <option value="">--</option>
                    <option value="ARGENTINA">Argentina</option>
                </select>
            </div>
            <div className="col-md-6">
            <label htmlFor="province" className="form-label">Provincia</label>
            <select
                className="form-select"
                id="province"
                name="province"
                value={userData.address.province}
                onChange={handleAddressChange}
            >
                <option value="">--</option>
                <option value="CIUDAD_DE_BUENOS_AIRES">Ciudad de Buenos Aires</option>
                <option value="SANTA_FE">Santa Fe</option>
                <option value="BUENOS_AIRES">Buenos Aires</option>
            </select>
            </div>
            <div className="col-md-6">
            <label htmlFor="city" className="form-label">Ciudad</label>
            <select
                className="form-select"
                id="city"
                name="city"
                value={userData.address.city}
                onChange={handleAddressChange}
            >
                <option value="">--</option>
                <option value="CIUDAD_DE_BUENOS_AIRES">Ciudad de Buenos Aires</option>
                <option value="ROSARIO">Rosario</option>
                <option value="LA_PLATA">La Plata</option>
            </select>
            </div>
            <div className="col-md-6">
                <label htmlFor="streetName" className="form-label">Calle</label>
                <input
                type="text"
                className="form-control"
                id="streetName"
                name="streetName"
                value={userData.address.streetName}
                onChange={handleAddressChange}
                />
            </div>
            <div className="col-md-6">
                <label htmlFor="streetNumber" className="form-label">Nro. Calle</label>
                <input
                type="text"
                className="form-control"
                id="streetNumber"
                name="streetNumber"
                value={userData.address.streetNumber}
                onChange={handleAddressChange}
                />
            </div>
            <div className="col-md-6">
                <label htmlFor="floorApt" className="form-label">Piso/Dpto.</label>
                <input
                type="text"
                className="form-control"
                id="floorApt"
                name="floorApt"
                value={userData.address.floorApt}
                onChange={handleAddressChange}
                />
            </div>
            <h2>Precio por hora</h2>
            <div className="col-md-6">
                <label htmlFor="ARS" className="form-label">ARS</label>
                <input
                type="text"
                className="form-control"
                id="ARS"
                name="ARS"
                value={userData.hourlyRates.ARS}
                onChange={handleHourlyRatesChange}
                />
            </div>
            <div className="col-md-6">
                <label htmlFor="USD" className="form-label">USD</label>
                <input
                type="text"
                className="form-control"
                id="USD"
                name="USD"
                value={userData.hourlyRates.USD}
                onChange={handleHourlyRatesChange}
                />
            </div>
            <h2>Modalidad</h2>
            <div className="col-md-6">
                <div className="form-check">
                    <label className="form-check-label">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="FACE_TO_FACE"
                        name="FACE_TO_FACE"
                        checked={userData.modalities.FACE_TO_FACE}
                        onChange={handleModalitiesChange}
                    />
                    Presencial
                    </label>
                </div>
            </div>
            <div className="col-md-6">
                <div className="form-check">
                    <label className="form-check-label">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="REMOTE"
                        name="REMOTE"
                        checked={userData.modalities.REMOTE}
                        onChange={handleModalitiesChange}
                    />
                    Remoto
                    </label>
                </div>
            </div>
            <h2>Especialidad</h2>
    
            <div className="col-md-6">
                <label htmlFor='masteryLevel' className="form-label">
                    Nivel
                </label>
                <select
                    className="form-select"
                    id='masteryLevel'
                    value={userData.proficiencies[0].masteryLevel}
                    onChange={(event) =>
                    handleProficiencyChange('masteryLevel', event.target.value)
                    }
                >
                    <option value="">--</option>
                    <option value="MIDDLE_SCHOOL">Escuela Intermedia</option>
                    <option value="HIGHSCHOOL">Escuela Secundaria</option>
                    <option value="COLLEGE">Universidad</option>
                </select>
            </div>
            <div className="col-md-6">
                <label htmlFor='subject' className="form-label">
                    Materia
                </label>
                <select
                    className="form-select"
                    id='subject'
                    value={userData.proficiencies[0].subject}
                    onChange={(event) =>
                    handleProficiencyChange('subject', event.target.value)
                    }
                >
                    <option value="">--</option>
                    <option value="MATH">Matemáticas</option>
                    <option value="ENGLISH">Inglés</option>
                    <option value="BIOLOGY">Biología</option>
                    <option value="HISTORY">Historia</option>
                    <option value="LITERATURE">Literatura</option>
                </select>
            </div>
            <h2>Horario Semanal</h2>
            <div className="col-md-6">
                <label htmlFor="checkIn" className="form-label">Check In</label>
                <input
                type="time"
                className="form-control"
                id="checkIn"
                name="checkIn"
                value={userData.weeklyWorkingSchedule.checkIn}
                onChange={handleWeeklyScheduleChange}
                />
            </div>
            <div className="col-md-6">
                <label htmlFor="checkOut" className="form-label">Check Out</label>
                <input
                type="time"
                className="form-control"
                id="checkOut"
                name="checkOut"
                value={userData.weeklyWorkingSchedule.checkOut}
                onChange={handleWeeklyScheduleChange}
                />
            </div>
            <div className="col-12 weekly__container">
                <div className="form-check">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="sunday"
                        name="sunday"
                        checked={userData.weeklyWorkingSchedule.sunday}
                        onChange={handleWeeklyScheduleChange}
                    />
                    <label className="form-check-label" htmlFor="sunday">
                        Domingo
                    </label>
                </div>
                <div className="form-check">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="monday"
                        name="monday"
                        checked={userData.weeklyWorkingSchedule.monday}
                        onChange={handleWeeklyScheduleChange}
                    />
                    <label className="form-check-label" htmlFor="monday">
                        Lunes
                    </label>
                </div>
                <div className="form-check">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="tuesday"
                        name="tuesday"
                        checked={userData.weeklyWorkingSchedule.tuesday}
                        onChange={handleWeeklyScheduleChange}
                    />
                    <label className="form-check-label" htmlFor="tuesday">
                        Martes
                    </label>
                </div>
                <div className="form-check">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="wednesday"
                        name="wednesday"
                        checked={userData.weeklyWorkingSchedule.wednesday}
                        onChange={handleWeeklyScheduleChange}
                    />
                    <label className="form-check-label" htmlFor="wednesday">
                        Miercoles
                    </label>
                </div>
                <div className="form-check">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="thursday"
                        name="thursday"
                        checked={userData.weeklyWorkingSchedule.thursday}
                        onChange={handleWeeklyScheduleChange}
                    />
                    <label className="form-check-label" htmlFor="thursday">
                        Jueves
                    </label>
                </div>
                <div className="form-check">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="friday"
                        name="friday"
                        checked={userData.weeklyWorkingSchedule.friday}
                        onChange={handleWeeklyScheduleChange}
                    />
                    <label className="form-check-label" htmlFor="friday">
                        Viernes
                    </label>
                </div>
                <div className="form-check">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        id="saturday"
                        name="saturday"
                        checked={userData.weeklyWorkingSchedule.saturday}
                        onChange={handleWeeklyScheduleChange}
                    />
                    <label className="form-check-label" htmlFor="saturday">
                        Sabado
                    </label>
                </div>
            </div>
            <div className="col-12">
                <h2>Profile Picture URL</h2>
                <label htmlFor="profilePictureUrl" className="form-label">
                URL de imagen
                </label>
                <input
                type="text"
                className="form-control"
                id="profilePictureUrl"
                name="profilePictureUrl"
                value={userData.profilePictureUrl}
                onChange={handleInputChange}
                />
            </div>
            <div className="col-12">
                <button type="submit" className="btn btn-primary">Agregar profesor</button>
            </div>
        </form>
    </div>
  );
}

export default TeacherForm;