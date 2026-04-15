import { useState } from 'react';

function MultiForm() {
    const [currentStep, setCurrentStep] = useState(1);
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');


    const handleChange = event => {
        const { name, value } = event.target;

        switch (name) {
            case 'email':
                setEmail(value);
                break;
            case 'username':
                setUsername(value);
                break;
            case 'password':
                setPassword(value);
                break;
            default:
                break;
        }
    };

    const handleSubmit = event => {
        event.preventDefault();

        alert(`Your registration detail:
        Email: ${email}
        Username: ${username}
        Password: ${password}`);

        setEmail('');
        setUsername('');
        setPassword('');
        setCurrentStep(1);
    };

    const nextButton = () => {
        if (currentStep < 3) {
            return (
                <button
                    className="btn btn-next f-right"
                    type="button"
                    onClick={() => setCurrentStep(prev => prev + 1)}
                >
                    Next
                </button>
            );
        }
        return null;
    };

    const previousButton = () => {
        if (currentStep > 1) {
            return (
                <button
                    className="btn btn-previous"
                    type="button"
                    onClick={() => setCurrentStep(prev => prev - 1)}
                >
                    Previous
                </button>
            );
        }
        return null;
    };

    return (
        <div className="container">
            <h1>React Wizard Form</h1>
            <p>Step {currentStep}</p>

            <form onSubmit={handleSubmit}>
                <Step1
                    currentStep={currentStep}
                    handleChange={handleChange}
                    email={email}
                />

                <Step2
                    currentStep={currentStep}
                    handleChange={handleChange}
                    username={username}
                />

                <Step3
                    currentStep={currentStep}
                    handleChange={handleChange}
                    password={password}
                />

                {nextButton()}
                {previousButton()}
            </form>
        </div>
    );
}

function Step1(props) {
    if (props.currentStep !== 1) return null;

    return (
        <div className="form-group">
            <label htmlFor="email">Email address</label>
            <input
                className="form-control"
                id="email"
                name="email"
                type="text"
                placeholder="Enter email"
                value={props.email}
                onChange={props.handleChange}
            />
        </div>
    );
}

function Step2(props) {
    if (props.currentStep !== 2) return null;

    return (
        <div className="form-group">
            <label htmlFor="username">Username</label>
            <input
                className="form-control"
                id="username"
                name="username"
                type="text"
                placeholder="Enter username"
                value={props.username}
                onChange={props.handleChange}
            />
        </div>
    );
}


function Step3(props) {
    if (props.currentStep !== 3) return null;

    return (
        <>
            <div className="form-group">
                <label htmlFor="password">Password</label>
                <input
                    className="form-control"
                    id="password"
                    name="password"
                    type="password"
                    placeholder="Enter password"
                    value={props.password}
                    onChange={props.handleChange}
                />
            </div>

            <button className="btn btn-submit f-right" type="submit">
                Sign up
            </button>
        </>
    );
}

export default MultiForm;