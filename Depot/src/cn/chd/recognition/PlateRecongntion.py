from hyperlpr import *
import cv2
if __name__ == '__main__' :	
	filename = sys.argv[1]
	image = cv2.imread(filename)
	res=HyperLPR_PlateRecogntion(image)
	if res:
		print(res[0][0])
	else: 
		print("")