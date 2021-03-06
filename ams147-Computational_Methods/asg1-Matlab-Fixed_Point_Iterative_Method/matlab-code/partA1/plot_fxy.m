%
% Plot f(x,y) = sin(x)*cos(0.5*y) for x in [-3,3] and y in [-4,4].
%
clear;
clf;
%
m=31;
n=31;
dx=6/(n-1);
dy=8/(m-1);
x1=[-3:dx:3];
y1=[-4:dy:4];
%
[x,y]=meshgrid(x1,y1);
%
f=sin(x).*cos(0.5*y);
%
h=surf(x,y,f);
set(h,'facecolor','interp')
set(h,'edgecolor',[0.5,0.5,0.5])
%
axis([-3.5,3.5,-4.5,4.5,-1.0,1.0])
set(gca,'xtick',[-3:1:3])
set(gca,'ytick',[-4:2:4])
set(gca,'ztick',[-1:0.5:1])
set(gca,'fontsize',14)
xlabel('x')
ylabel('y')
zlabel('f(x,y)')
%
