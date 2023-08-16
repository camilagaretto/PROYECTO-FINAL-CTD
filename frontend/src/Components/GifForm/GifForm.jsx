import React from 'react'
import Gif from '../../assets/FormGif.gif'
import './GifForm.scss'

const GifForm = ({title}) => {
  return (
    <div className='gif-container'>
        <h1>{title}</h1>
        <hr />
        <img src={Gif} alt="Appkademy Gif" />
    </div>
  )
}

export default GifForm