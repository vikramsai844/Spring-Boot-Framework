import { useState } from 'react'
import '../assets/style/UseStateExample.css'
function ParentComponent() {

    const [name, setName] = useState('')

    function handleEvents() {
        setName('Ram')
    }



    return (
        <>
            <div className='card'>
                <h1>Hello <span className='value'>{name}</span></h1>
                <button className='changeBtn' onClick={handleEvents}>Change Name</button>
            </div>

        </>
    )
}
export default ParentComponent;