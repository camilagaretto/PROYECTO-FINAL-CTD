import React from 'react'
import './Card.scss'
import { motion } from "framer-motion"

const Card = ({teacher}) => {
    return (
        <motion.div 
        layout
        animate={{ opacity: 1 }}
        initial={{ opacity: 0 }}
        exit={{ opacity: 0 }}
        transition={{ duration: 0.5 }}
        >
        <div className='card__container' style={{backgroundImage : `url(${teacher.profilePictureUrl})`}}>
            <p><span>{teacher.shortDescription}</span></p>
            <p>{teacher.firstName} {teacher.lastName}</p>
        </div>
        </motion.div>
    )
}

export default Card