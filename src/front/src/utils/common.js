export function GenerateLiElUUID(){
    return crypto.randomUUID().replaceAll("-", "");
}

/**
 * @brief 이메일 정규식 형태가 일치하는지 확인합니다.
 * The email couldn't start or finish with a dot
 * The email shouldn't contain spaces into the string
 * The email shouldn't contain special chars (<:, *,ecc)
 * The email could contain dots in the middle of mail address before the @
 * The email could contain a double doman ( '.de.org' or similar rarity)
 * @param {String} inputString 
 * @returns boolean
 */
export function correctRegxEmail(inputString){
    const emailRegx = new RegExp(/^((?!\.)[\w\-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$/gm);
    return emailRegx.test(inputString)
}

/**
 * @brief 비밀번호 정규식 형태가 일치하는지 확인합니다.
 * 형태는
 * password must contain 1 number (0-9)
 * password must contain 1 uppercase letters
 * password must contain 1 lowercase letters
 * password must contain 1 non-alpha numeric number
 * password is 8-16 characters with no space
 * @param {String} inputString 
 * @returns boolean
 */
export function correctRegxPwd(inputString){
    const pwdRegx = new RegExp(/^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}$/gm);
    return pwdRegx.test(inputString);
}

export function removeHtmlLabels(inputString){
    const reg = /<[^>]*>?/g;
    return inputString.replace(reg,'');
}


/**
 * anchor 태그 scroll 이벤트 콜백
 */

export function anchorScrollCallback(e){
    e.preventDefault(); // [2] a태그의 주소이동 막고
    let MemuData = (e.target).hash; // #HOME, #ABOUT 등
    const IdMatchedPage = document.querySelector(MemuData);

    // [3] 해당 id를 갖은 요소에 scrollIntoview 메서드를 사용한다.
    IdMatchedPage?.scrollIntoView({ behavior: "smooth" });
};

/**
 * 
 * @param {Date} date 
 */
export function FullDateFormatString(date){
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    second = second >= 10 ? second : '0' + second;

    return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
};