import React, { useEffect, useState } from 'react';
import { Button } from '../ui/button';
import { User } from '@/types/User';

const LogoLogin: React.FC = () => {
    const [user, setUser] = useState<User | undefined>(undefined);

    const loginHandler = () => {
        window.location.href = "/sign-in"
    };

    const signInHandler = () => {
       window.location.href = "/sign-up"
    };

    const logoutHandler = () => {
        sessionStorage.removeItem('user');
        setUser(undefined); // 로그아웃 후 사용자 상태를 undefined로 설정
    };

    useEffect(() => {
        const sessionUser = sessionStorage.getItem('user');
        if (sessionUser) {
            try {
                const parsedUser = JSON.parse(sessionUser) as User;
                setUser(parsedUser);
            } catch (error) {
                console.error('Failed to parse user from sessionStorage', error);
                setUser(undefined);
            }
        } else {
            setUser(undefined);
        }
    }, []);

    return (
        <div className='flex justify-evenly items-center'>
            <div></div>
            <div className='text-[40px] font-bold cursor-pointer' onClick={() => window.location.href = "/"}>
                CODE ARENA
            </div>
            <div className='flex'>
                {user ? (
                    <div>
                        <Button onClick={logoutHandler}>로그아웃</Button>
                    </div>
                ) : (
                    <>
                        <div className='mr-5'>
                            <Button onClick={loginHandler}>로그인</Button>
                        </div>
                        <div>
                            <Button onClick={signInHandler}>회원가입</Button>
                        </div>
                    </>
                )}
            </div>
        </div>
    );
};

export default LogoLogin;
