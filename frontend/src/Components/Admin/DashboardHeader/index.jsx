import React from 'react';

import './styles.css';
import NotificationIcon from '../../../assets/icons/notification.svg';
import SettingsIcon from '../../../assets/icons/settings.svg';

function DashboardHeader ({ btnText, onClick }) {
    return(
        <div className='dashbord-header-container'>
             <div className='dashbord-header-left'>
                <h1>Panel de gestión</h1>
            </div>
            <div className='dashbord-header-right'>
            </div>
        </div>
    )
}

export default DashboardHeader;