import React, { useEffect, useState } from 'react';
import { Button } from '../ui/button';
import { useAuth } from '@/context/AuthContext';
import { requestLogout } from '@/api/requestLogOut';

const LogoLogin: React.FC = () => {
    const { user, logout } = useAuth();


    const loginHandler = () => {
        window.location.href = "/sign-in"
    };

    const signInHandler = () => {
       window.location.href = "/sign-up"
    };

    const logoutHandler = () => {
        requestLogout().then(()=> 
            logout()
        )
    };


    return (
        <div className='flex justify-evenly items-center'>
            <div></div>
            <div className='text-[40px] font-bold cursor-pointer' onClick={() => window.location.href = "/"}>
                CODE ARENA
            </div>
            <div className='flex'>
                {user ? (
                    <div className='flex items-center '>
                        <div className='mr-10'>{user.nickname}님 환영합니다.</div>
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
