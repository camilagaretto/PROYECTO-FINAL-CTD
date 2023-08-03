import React from 'react'
import './Card.css'
import { Link } from 'react-router-dom'

const Card = () => {
    return (
        <div className='card__container'>
            <div className='card__img'>
                <p><span>Juan</span></p>
                <p>Matemático</p>
            </div>
            <div className='card__information'>
                <p className='favs'>💖 3000 Fav</p>
                <p>Aficionado por los números y las matemáticas exactas...</p>
            </div>
            <div className='card__link__container'>
                <Link to="/" className='card__link'>Ver más</Link>
            </div>
        </div>
    )
}

export default Card