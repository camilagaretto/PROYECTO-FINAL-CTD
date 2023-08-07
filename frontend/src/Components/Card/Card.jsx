import React from 'react'
import './Card.scss'
import { Link } from 'react-router-dom'
import { motion } from "framer-motion"

const Card = ({movie}) => {
    return (
        <motion.div 
        layout
        animate={{ opacity: 1 }}
        initial={{ opacity: 0 }}
        exit={{ opacity: 0 }}
        transition={{ duration: 0.5 }}
        >
        <div className='card__container'>
            <div className='card__img'>
                <p><span>Juan</span></p>
                <p>Matemático</p>
            </div>
            <div className='card__information'>
                <p className='favs'>💖 3000 Fav</p>
                <p>{movie.title}</p>
            </div>
            <div className='card__link__container'>
                <Link to="/" className='btn btn-primary'>Ver más</Link>
            </div>
        </div>
        </motion.div>
    )
}

export default Card