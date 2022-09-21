from firebase import firebase

import cv2
import numpy as np
import face_recognition
import os
import time

firebase = firebase.FirebaseApplication('https://groupnine-e51bc-default-rtdb.firebaseio.com/', None)
# khai bao mang chua file anh
path = 'D:\Download\people'
images = []
# tao className de luu ten thuc te
className = []
# tao mang myList de luu ten anh ( duoi jpg)
myList = os.listdir(path)
#print(myList)
for cl in myList:
    curImg = cv2.imread(f'{path}/{cl}')
    images.append(curImg)
    # luu ten hoan chinh ( ko con chu "jpg")
    className.append(os.path.splitext(cl)[0])
#print(className)
# xay dung ham tim anh da dc ma hoa
def findEncoding(images):
    # tao 1 list trong de luu anh dc ma hoa
    encodeList = []
    for img in images:
        # convert tu BGR -> RGB
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        # tim anh encoding
        encode = face_recognition.face_encodings(img)[0]
        # them anh vua ma hoa vao list da tao san
        encodeList.append(encode)
    return encodeList
# goi ham
encodeListKnown = findEncoding(images)
# check so luong anh dc ma hoa
#print(len(encodeListKnown))
print("Ma hoa da dc hoan thanh !!!")
# ham main
# su dung camera
cap = cv2.VideoCapture(0)
namelast = " "
while True:
    success, img = cap.read()
    # giam kich thuoc anh =1/4 anh ban dau de tang toc do
    imgS = cv2.resize(img, (0,0), None, 0.25, 0.25)
    imgS = cv2.cvtColor(imgS, cv2.COLOR_BGR2RGB)
    # do trong cam co the co nhieu face, do do can xac dinh dc vi tri face can kiem tra
    facesCurFrame = face_recognition.face_locations(imgS)
    encodesCurFrame = face_recognition.face_encodings(imgS, facesCurFrame)

    for encodeFace, faceLoc in zip(encodesCurFrame, facesCurFrame):
        # so sanh anh da dc ma hoa qua camera voi list anh ma hoa
        matches = face_recognition.compare_faces(encodeListKnown, encodeFace)
        faceDis = face_recognition.face_distance(encodeListKnown, encodeFace)
        # -> distance thap nhat se cho ket qua trung khop nhat
       # print(faceDis)
        matchIndex = np.argmin(faceDis)

        # neu check dung nguoi, viet ten cho nguoi do
        if matches[matchIndex]:
            name = className[matchIndex]
            if name != namelast or time.time() - now > 60:
                localtime = time.asctime(time.localtime(time.time()))
                data = {'Name': name,
                        'time': localtime
                        }
                result = firebase.post('Attendence/', data)
                print(result)
                #firebase.put('User_infor/1','attendence',localtime);
                print(localtime)
                now = time.time()
            namelast = name
            #print(name)
            # tao 1 box de chen name vao camera
            y1,x2,y2,x1 = faceLoc
            # do ban dau ma hoa anh camera = 1/4 anh goc nen gio phai khoi phuc kich thuoc ban dau
            y1, x2, y2, x1 = y1*4,x2*4,y2*4,x1*4
            cv2.rectangle(img, (x1,y1), (x2,y2) , (0,255,0), 2)
            cv2.rectangle(img, (x1,y2-35), (x2,y2), (0,255,0), cv2.FILLED)
            cv2.putText(img, name, (x1-6,  y2-6), cv2.FONT_HERSHEY_COMPLEX, 1, (255, 255,255), 2)
        else:
            y1, x2, y2, x1 = faceLoc
            # do ban dau ma hoa anh camera = 1/4 anh goc nen gio phai khoi phuc kich thuoc ban dau
            y1, x2, y2, x1 = y1 * 4, x2 * 4, y2 * 4, x1 * 4
            cv2.rectangle(img, (x1, y1), (x2, y2), (0, 255, 0), 2)

    cv2.imshow('Webcam', img)
    cv2.waitKey(1)
