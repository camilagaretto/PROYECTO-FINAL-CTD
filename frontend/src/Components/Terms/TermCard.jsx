import React from 'react'
import './TermCard.scss'


const TermCard = ({image, title, description}) => {
  return (
    <div className='term-card'>
        <img src={image} alt="Appkademy Term Card Image" />
        <h3>{title}</h3>
        <p>{description}</p>
    </div>
  )
}

export default TermCard