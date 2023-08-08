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
            <p><span>Juan</span></p>
            <p>{movie.title}</p>
        </div>
        </motion.div>
    )
}

export default Card