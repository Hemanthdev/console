$wnd.hal.runAsyncCallback37("function K1g(){J1g()}\nfunction I3g(){H3g()}\nfunction L3g(){K3g()}\nfunction O3g(){N3g()}\nfunction H3g(){H3g=sHc}\nfunction K3g(){K3g=sHc}\nfunction N3g(){N3g=sHc}\nfunction J1g(){J1g=sHc}\nfunction X1g(){X1g=sHc}\nfunction $1g(){$1g=sHc}\nfunction b2g(){b2g=sHc}\nfunction e2g(){e2g=sHc}\nfunction h2g(){h2g=sHc}\nfunction k2g(){k2g=sHc}\nfunction n2g(){n2g=sHc}\nfunction z1g(){z1g=sHc;GEe()}\nfunction B3g(){B3g=sHc;fDe()}\nfunction M1g(){M1g=sHc;nb();ksk()}\nfunction f2g(a){e2g();this.a=a}\nfunction i2g(a){h2g();this.a=a}\nfunction Y1g(a,b){X1g();this.b=a;this.a=b}\nfunction c2g(a,b,d){b2g();this.a=a;this.b=b;this.c=d}\nfunction o2g(a,b,d,e){n2g();this.b=a;this.d=b;this.c=d;this.a=e}\nfunction l2g(a,b,d,e,g){k2g();this.e=a;this.b=b;this.c=d;this.d=e;this.a=g}\nfunction _1g(a,b,d,e,g){$1g();this.c=a;this.d=b;this.e=d;this.a=e;this.b=g}\nfunction O1g(a,b,d,e){M1g();this.a=a;this.c=b;this.b=d;this.d=e;ub.call(this);this.WBb()}\nfunction v1g(a){s1g();ZWe.call(this,a);this.QBb()}\nfunction F1g(a,b){z1g();return new D3g(b,a)}\nfunction F3g(a){B3g();return new qtk(lmd('imap')+' '+'Socket Binding',a.RBb('imap'))}\nfunction E3g(a){B3g();return new qtk(lmd('smtp')+' '+'Socket Binding',a.RBb('smtp'))}\nfunction G3g(a){B3g();return new qtk(lmd('pop3')+' '+'Socket Binding',a.RBb('pop3'))}\nfunction I1g(a,b,d,e,g){z1g();{i_l(a,K$l(b.c1c().UUc('Mail Session',d)));e.I_(d)}}\nfunction G1g(a,b){z1g();var d;{d=o5(b.get('mail-session').g4().md().kM(new K1g).eM(_Md()),22);a.Gj(d)}}\nfunction C1g(a,b,d,e){z1g();var g,h;{g=(r1g(),n1g).resolve(a);h=(new Hbl(g,'read-resource')).sIc('recursive',true).build();b.NIc(h,new i2g(e))}}\nfunction H1g(a,b,d,e,g,h,i){z1g();var j,k;{if(R5(i)){j=(r1g(),m1g).resolve(a,h);k=(new Hbl(j,'add')).qIc('mail-session',h).payload(i).build();b.NIc(k,new o2g(d,e,h,g))}}}\nfunction D1g(a,b,d,e,g,h){z1g();var i,j;{j=a.rLc((r1g(),m1g));i=new twk((RLl(),yIl),b.c1c().VUc('Mail Session'),j,Utd(P3(B3(Qib,1),{4:1,1:1,5:1,6:1},2,6,['jndi-name','from','debug'])),new l2g(d,e,g,b,h));i.Dy()}}\nfunction D3g(a,b){B3g();var d;kDe.call(this,a.name,a.SBb().isEmpty()?b.a1c().SRc():b.c1c().qVc(Tg(', ').Yc(a.SBb())).eu());this.mCb();d=new Rsk(a);d.pxc('jndi-name');if(a.TBb('smtp')){d.rxc(new I3g)}if(a.TBb('imap')){d.rxc(new L3g)}if(a.TBb('pop3')){d.rxc(new O3g)}d.sxc();this.G$().UM(d)}\nfunction B1g(a,b,d,e,g,h,i,j,k){z1g();IEe.call(this,(new ink(a,'mail-session','Mail Session')).Bvc().Avc());this.UBb();this.U_(new Y1g(h,g));this.c_(b.ouc((RLl(),xIl),'Mail Session',(r1g(),m1g),new _1g(i,k,h,g,e)));this.c_(b.ruc((RLl(),BIl)));this.R_(new c2g(this,d,j));this.W_(new f2g(k))}\nqHc(910,10,{1:1,16:1,10:1},v1g);_.RBb=function w1g(a){var b;b=lal(this,'server'+'/'+a+'/'+'outbound-socket-binding-ref');return b.defined?b.asString():'n/a'};_.SBb=function x1g(){var a;a=new mtd;if(this.TBb('smtp')){a.add(lmd('smtp'))}if(this.TBb('imap')){a.add(lmd('imap'))}if(this.TBb('pop3')){a.add(lmd('pop3'))}return a};_.TBb=function y1g(a){return this.hasDefined('server')&&this.get('server').hasDefined(a)};qHc(3446,36,{1:1,12:1,36:1},B1g);_.UBb=function A1g(){};_.VBb=function E1g(a,b,d){z1g();return new O1g(this,d,a,b)};var JTb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn',3446,wtc);qHc(3447,1,{1:1},K1g);_.Tc=function L1g(a){return new v1g(o5(a,46))};var ATb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/0methodref$ctor$Type',3447,Jib);qHc(3453,1,{1:1,12:1},O1g);_.WBb=function N1g(){};_.n0=function S1g(){return osk(this)};_.Ml=function T1g(){return psk(this)};_.o0=function V1g(){return qsk(this)};_.s0=function W1g(){return rsk(this)};_.BW=function P1g(){var a;a=new mtd;a.add(this.b.gxc(this.d.FCc('mail-session').WE('name',this.c.name).TE()));a.add(this.b.cxc('Mail Session',this.c.name,(r1g(),m1g),this.a));return a};_.QN=function Q1g(){if(!this.c.SBb().isEmpty()){return ssk(this.c.name,Tg(', ').Yc(this.c.SBb()))}return null};_.m0=function R1g(){var a;a=new mtd;a.add(this.c.name);a.addAll(this.c.SBb());return Nld(' ',a)};_.Nl=function U1g(){return this.c.name};var BTb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/1',3453,Jib);qHc(3449,1,{1:1},Y1g);_.z0=function Z1g(a,b){C1g(this.b,this.a,a,b)};var CTb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/lambda$0$Type',3449,Jib);qHc(3452,1,{1:1},_1g);_.B0=function a2g(a){D1g(this.c,this.d,this.e,this.a,this.b,a)};var DTb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/lambda$1$Type',3452,Jib);qHc(3454,1,{1:1},c2g);_.A0=function d2g(a){return this.a.VBb(this.b,this.c,a)};var ETb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/lambda$2$Type',3454,Jib);qHc(3455,1,{1:1},f2g);_.y0=function g2g(a){return F1g(this.a,a)};var FTb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/lambda$3$Type',3455,Jib);qHc(3448,1,{1:1,34:1},i2g);_.Gj=function j2g(a){G1g(this.a,a)};var GTb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/lambda$4$Type',3448,Jib);qHc(3451,1,{1:1},l2g);_.I1=function m2g(a,b){H1g(this.e,this.b,this.c,this.d,this.a,a,b)};var HTb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/lambda$5$Type',3451,Jib);qHc(3450,1,{1:1,34:1},o2g);_.Gj=function p2g(a){I1g(this.b,this.d,this.c,this.a,a)};var ITb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionColumn/lambda$6$Type',3450,Jib);qHc(4852,33,{1:1,8:1,33:1},D3g);_.mCb=function C3g(){};var aUb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionPreview',4852,suc);qHc(4853,1,{1:1,98:1},I3g);_.l7=function J3g(a){return E3g(a)};var ZTb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionPreview/lambda$0$Type',4853,Jib);qHc(4854,1,{1:1,98:1},L3g);_.l7=function M3g(a){return F3g(a)};var $Tb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionPreview/lambda$1$Type',4854,Jib);qHc(4855,1,{1:1,98:1},O3g);_.l7=function P3g(a){return G3g(a)};var _Tb=nhd('org.jboss.hal.client.configuration.subsystem.mail','MailSessionPreview/lambda$2$Type',4855,Jib);qHc(1750,1,{1:1});_.yCb=function G4g(){var a;a=this.HCb(this.a.CA().Qxc(),this.a.CA().Oxc(),this.a.CA().Sxc(),this.a.xz().yw(),this.a.MA().WJc(),this.a.OA().NLc(),this.a.OA().MLc(),this.a.GA().JCc(),this.a.UA().p1c());this.DCb(a);return a};_.DCb=function M4g(a){};_.HCb=function Q4g(a,b,d,e,g,h,i,j,k){return new B1g(a,b,d,e,g,h,i,j,k)};qHc(1754,1,{44:1,1:1});_.fk=function i5g(){this.b.Gj(this.a.a.yCb())};qHc(161,1,{1:1,166:1});_.SRc=function YBl(){return 'No configured mail servers found.'};qHc(261,1,{1:1,298:1});_.qVc=function JNl(a){return (new jLc).ku('Configured mail servers: ').ju(a).lu()};R0l(zJ)(37);\n//# sourceURL=hal-37.js\n")