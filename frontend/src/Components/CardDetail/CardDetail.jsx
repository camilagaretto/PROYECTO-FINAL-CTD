import React from 'react'
import './CardDetail.scss'

const CardDetail = ({fullName, hourlyRates, characteristics}) => {
    return (
        <div className='card__detail__container'>
            <div>
                <img src="https://res.cloudinary.com/tuko/image/upload/v1691591701/14_nrivov.svg" alt="Appkademy teacher image" />
            </div>
            <div className='card__detail__text'>
                <div>
                    <h4>{fullName}</h4>
                    {/* <p>+50 Estudiantes</p>
                    <p>+200 Clases dictadas</p> */}
                    {characteristics?.map(characteristic=>(
                        <p key={characteristic.id}>{characteristic.name}</p>
                    ))}
                </div>
                <div>
                    <h2>$25</h2>
                    <h3>Hora</h3>
                </div>
            </div>
        </div>
    )
}

export default CardDetail