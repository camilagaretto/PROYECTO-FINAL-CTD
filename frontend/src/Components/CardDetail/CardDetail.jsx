import React from 'react'
import './CardDetail.scss'

const CardDetail = ({fullName, hourlyRates, characteristics, profileImg}) => {
    return (
        <div className='card__detail__container'>
            <div>
                <img src={profileImg} alt="Appkademy teacher image" />
            </div>
            <div className='card__detail__text'>
                <div>
                    <h4>{fullName}</h4>
                    {/* <p>+50 Estudiantes</p>
                    <p>+200 Clases dictadas</p> */}
                    {characteristics?.map(characteristic=>(
                        <p key={characteristic.id}>{characteristic.icon} {characteristic.name}</p>
                    ))}
                </div>
                <div>
                    <h3>${hourlyRates[0]?.value}</h3>
                    <h4>Hora</h4>
                </div>
            </div>
        </div>
    )
}

export default CardDetail