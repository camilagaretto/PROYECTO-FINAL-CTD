import React from 'react'
import './Step.scss'

const Steps = ({num, img, title, description}) => {
  return (
    <div className='step-card'>
        <img className='step-number' src={num} alt="Appkademy numeracion de pasos" />
        <div className='step-card-img'>
            <img src={img} alt="Vector pasos a seguir" />
        </div>
        <h3>{title}</h3>
        <p>{description}</p>
    </div>
  )
}

export default Steps