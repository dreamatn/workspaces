package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSTAR;
import com.hisun.SC.SCRCWAT;
import com.hisun.SO.SORSYS;

public class CMZOBSP {
    boolean pgmRtn = false;
    Object CWA_PTR;
    short WS_CNT = 0;
    short WS_POS = 0;
    int RESP = 0;
    CMZOBSP_WS_PROC_INFO WS_PROC_INFO = new CMZOBSP_WS_PROC_INFO();
    CMZOBSP_WS_MSGID WS_MSGID = new CMZOBSP_WS_MSGID();
    short WS_PARM_LEN = 0;
    SCCMSG SCCMSG = new SCCMSG();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCRWSPC SCCRWSPC = new SCCRWSPC();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCRWBTL SCCRWBTL = new SCCRWBTL();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCRBBTL SCRBBTL = new SCRBBTL();
    SORSYS SORSYS = new SORSYS();
    SCRCWA SCRPCWA = new SCRCWA();
    SCRCWAT SCRCWAT = new SCRCWAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    SCCBSP SCCBSP;
    public void MP(SCCGWA SCCGWA, SCCBSP SCCBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCBSP = SCCBSP;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCBSP);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZOBSP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        T000_READ_UPD_SCTCWA();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'A' 
            && SCCGAPV.TYPE == ' ') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCBSP.SERV_CODE.trim().length() > 0) {
            B000_MAIN_PROCESS();
            if (pgmRtn) return;
        } else {
            if (SCCBSP.AP_PROC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.H054, WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                C000_MAIN_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCRWBTL);
        SCCRWBTL.PTR = SCRBBTL;
        SCCRWBTL.LEN = 837;
        SCRBBTL.KEY.SERV_CODE = SCCBSP.SERV_CODE;
        SCRBBTL.KEY.AP_TYPE = SCCBSP.BH_SEQ.BH_TYPE;
        SCRBBTL.KEY.AP_BATNO = SCCBSP.BH_SEQ.BH_BATNO;
        SCCRWBTL.FUNC = 'R';
        S000_CALL_SCZRWBTL();
        if (pgmRtn) return;
        SCRBBTL.PARM_DA1 = SCCBSP.PARM_DA1;
        SCRBBTL.PARM_DA2 = SCCBSP.PARM_DA2;
        SCRBBTL.PARM_DA3 = SCCBSP.PARM_DA3;
        SCRBBTL.PARM_DA4 = SCCBSP.PARM_DA4;
        SCRBBTL.PARM_DA5 = SCCBSP.PARM_DA5;
        SCCRWBTL.FUNC = 'U';
        S000_CALL_SCZRWBTL();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.AC_DATE <= SCRBBTL.FIN_AC_DATE 
            && SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_PROC_NAME.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_ERR_BSP_HAD_RUN, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_PROC_INFO.WS_BH_FLG = 'Y';
        WS_PROC_INFO.WS_PROC_NUM = 0;
        if ((SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_STS == 'E' 
            || SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_STS == 'P' 
            || SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_STS == ' ') 
            && SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_PROC_NAME.trim().length() > 0) {
            WS_PROC_INFO.WS_PROC_NUM = (short) (WS_PROC_INFO.WS_PROC_NUM + 1);
            WS_PROC_INFO.WS_PROC_INF.WS_PROC_NAME[1-1] = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_PROC_NAME;
        }
        if ((SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_STS == 'E' 
            || SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_STS == 'P' 
            || SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_STS == ' ') 
            && SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_PROC_NAME.trim().length() > 0) {
            WS_PROC_INFO.WS_PROC_NUM = (short) (WS_PROC_INFO.WS_PROC_NUM + 1);
            WS_PROC_INFO.WS_PROC_INF.WS_PROC_NAME[2-1] = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_PROC_NAME;
        }
        if ((SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_STS == 'E' 
            || SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_STS == 'P' 
            || SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_STS == ' ') 
            && SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_PROC_NAME.trim().length() > 0) {
            WS_PROC_INFO.WS_PROC_NUM = (short) (WS_PROC_INFO.WS_PROC_NUM + 1);
            WS_PROC_INFO.WS_PROC_INF.WS_PROC_NAME[3-1] = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_PROC_NAME;
        }
        WS_PROC_INFO.WS_SCENE_FLG = SCCBSP.SCENE_FLG;
        WS_PROC_INFO.WS_AP_TYPE = SCCBSP.BH_SEQ.BH_TYPE;
        WS_PROC_INFO.WS_SYS_ID = SCCBSP.BH_SEQ.SYS_ID;
        WS_PROC_INFO.WS_PARM_DA1 = SCCBSP.PARM_DA1;
        WS_PROC_INFO.WS_PARM_DA2 = SCCBSP.PARM_DA2;
        WS_PROC_INFO.WS_PARM_DA3 = SCCBSP.PARM_DA3;
        WS_PROC_INFO.WS_PARM_DA4 = SCCBSP.PARM_DA4;
        WS_PROC_INFO.WS_PARM_DA5 = SCCBSP.PARM_DA5;
        if (SCCBSP.BH_SEQ.BH_TYPE.trim().length() > 0) {
            JIBS_tmp_str[0] = "" + SCCBSP.BH_SEQ.BH_BATNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1).trim().length() == 0) WS_PROC_INFO.WS_PROC_DD = 0;
            else WS_PROC_INFO.WS_PROC_DD = Short.parseShort(JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1));
        } else {
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_PROC_INFO.WS_PROC_DD = 0;
            else WS_PROC_INFO.WS_PROC_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
        if (SCRCWAT.AC_DATE_AREA[1-1].AC_DATE == SCRCWAT.AC_DATE_AREA[2-1].AC_DATE) {
            D000_SUB_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SCCCTLM_MSG.SC_SYSTEM_BUSY, WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void C000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        WS_PROC_INFO.WS_PROC_NUM = 1;
        WS_PROC_INFO.WS_PROC_INF.WS_PROC_NAME[1-1] = SCCBSP.AP_PROC;
        WS_PROC_INFO.WS_BH_FLG = ' ';
        WS_PROC_INFO.WS_SCENE_FLG = SCCBSP.SCENE_FLG;
        WS_PROC_INFO.WS_AP_TYPE = SCCBSP.BH_SEQ.BH_TYPE;
        CEP.TRC(SCCGWA, SCCBSP.BH_SEQ.BH_TYPE);
        WS_PROC_INFO.WS_SYS_ID = SCCBSP.BH_SEQ.SYS_ID;
        WS_PROC_INFO.WS_PARM_DA1 = SCCBSP.PARM_DA1;
        WS_PROC_INFO.WS_PARM_DA2 = SCCBSP.PARM_DA2;
        WS_PROC_INFO.WS_PARM_DA3 = SCCBSP.PARM_DA3;
        WS_PROC_INFO.WS_PARM_DA4 = SCCBSP.PARM_DA4;
        WS_PROC_INFO.WS_PARM_DA5 = SCCBSP.PARM_DA5;
        if (SCCBSP.BH_SEQ.BH_TYPE.trim().length() > 0) {
            JIBS_tmp_str[0] = "" + SCCBSP.BH_SEQ.BH_BATNO;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<12-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1).trim().length() == 0) WS_PROC_INFO.WS_PROC_DD = 0;
            else WS_PROC_INFO.WS_PROC_DD = Short.parseShort(JIBS_tmp_str[0].substring(11 - 1, 11 + 2 - 1));
        } else {
            JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) WS_PROC_INFO.WS_PROC_DD = 0;
            else WS_PROC_INFO.WS_PROC_DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
        CEP.TRC(SCCGWA, SCCBSP.SCENE_FLG);
        D000_SUB_PROCESS();
        if (pgmRtn) return;
    }
    public void D000_SUB_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSYS);
        WS_PARM_LEN = 357;
        SORSYS.KEY.SYS_ID = " ";
        T000_READ_SOTSYS();
        if (pgmRtn) return;
        WS_PROC_INFO.WS_PROC_ENV = SORSYS.ENV_ID;
        CEP.TRC(SCCGWA, WS_PROC_INFO.WS_SYS_ID);
        CEP.TRC(SCCGWA, WS_PROC_INFO);
        WS_STAR_COMM = new SCZEXIT_WS_STAR_COMM();
        WS_STAR_COMM.STAR_PGM = "SCZOBSPS";
        WS_STAR_COMM.STAR_DATA = WS_PROC_INFO;
        WS_STAR_COMM.STAR_LEN = WS_PARM_LEN;
        IBS.START(SCCGWA, WS_STAR_COMM, true);
        CEP.TRC(SCCGWA, WS_PARM_LEN);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        Z_RET();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCZRWSPC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-R-UPD-BSP-CTL", SCCRWSPC);
        if (SCCRWSPC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRWSPC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZRWBTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-R-MAINTAIN-BBTL", SCCRWBTL);
        CEP.TRC(SCCGWA, "AFTER-CALL-SCZRWBTL");
        CEP.TRC(SCCGWA, SCCRWBTL.RC.RC_CODE);
        if (SCCRWBTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRWBTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_SOTSYS() throws IOException,SQLException,Exception {
        SOTSYS_RD = new DBParm();
        SOTSYS_RD.TableName = "SOTSYS";
        IBS.READ(SCCGWA, SORSYS, SOTSYS_RD);
    }
    public void T000_READ_UPD_SCTCWA() throws IOException,SQLException,Exception {
        SCRPCWA.KEY.BANK_NO = SCCGWA.COMM_AREA.TR_BANK;
        SCTCWA_RD = new DBParm();
        SCTCWA_RD.TableName = "SCTCWA";
        IBS.READ(SCCGWA, SCRPCWA, SCTCWA_RD);
