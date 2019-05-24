package com.hisun.CM;

import com.hisun.SM.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT9902 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_Q_MAX_CNT = 2000;
    char WS_END_CNTL_FLG = ' ';
    String WS_TS_REC = " ";
    CMOT9902_WS_OUT_DATA WS_OUT_DATA = new CMOT9902_WS_OUT_DATA();
    CMOT9902_WS_TS_CNTL WS_TS_CNTL = new CMOT9902_WS_TS_CNTL();
    short WS_LEN = 0;
    CMOT9902_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMOT9902_WS_TEMP_VARIABLE();
    CMCF807 CMCF807 = new CMCF807();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    CMRFCNTT CMRFCNTT = new CMRFCNTT();
    CMCFCNTL CMCFCNTL = new CMCFCNTL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    CMB9902_AWA_9902 CMB9902_AWA_9902;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMOT9902 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB9902_AWA_9902>");
        CMB9902_AWA_9902 = (CMB9902_AWA_9902) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, CMRFCNTT);
        IBS.init(SCCGWA, CMCFCNTL);
        CMCFCNTL.RC.RC_APP = "CM";
        CMCFCNTL.DAT_PTR = CMRFCNTT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "MAIN BEGIN!");
        CEP.TRC(SCCGWA, CMB9902_AWA_9902.BU_TYPE);
        CEP.TRC(SCCGWA, CMB9902_AWA_9902.SYS_ID);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (CMB9902_AWA_9902.BU_TYPE.trim().length() == 0 
            && CMB9902_AWA_9902.SYS_ID.trim().length() == 0) {
            B100_BROWSE_PROCESS();
            if (pgmRtn) return;
        }
        if (CMB9902_AWA_9902.BU_TYPE.trim().length() > 0 
            && CMB9902_AWA_9902.SYS_ID.trim().length() > 0) {
            B110_READ_PROCESS();
            if (pgmRtn) return;
        }
        if (CMB9902_AWA_9902.BU_TYPE.trim().length() > 0 
            && CMB9902_AWA_9902.SYS_ID.trim().length() == 0) {
            B120_STARTBR_BY_TYPE_PROCESS();
            if (pgmRtn) return;
        }
        if (CMB9902_AWA_9902.BU_TYPE.trim().length() == 0 
            && CMB9902_AWA_9902.SYS_ID.trim().length() > 0) {
            B130_STARTBR_BY_SYSID_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B100_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B005_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_END_CNTL_FLG = 'N';
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '0';
        CMRFCNTT.BU_TYPE = CMB9902_AWA_9902.BU_TYPE;
        CMRFCNTT.SYS_ID = CMB9902_AWA_9902.SYS_ID;
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '1';
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        while (CMRFCNTT.END_FLG != 'Y' 
            && WS_END_CNTL_FLG != 'Y') {
            B025_OUT_RECORD();
            if (pgmRtn) return;
            CMCFCNTL.FUNC = '5';
            CMCFCNTL.FUNC2 = '1';
            S000_CALL_CMZFCNTL();
            if (pgmRtn) return;
        }
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '2';
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B120_STARTBR_BY_TYPE_PROCESS() throws IOException,SQLException,Exception {
        B005_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_END_CNTL_FLG = 'N';
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '3';
        CMRFCNTT.BU_TYPE = CMB9902_AWA_9902.BU_TYPE;
        CMRFCNTT.SYS_ID = CMB9902_AWA_9902.SYS_ID;
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '1';
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        while (CMRFCNTT.END_FLG != 'Y' 
            && WS_END_CNTL_FLG != 'Y') {
            B025_OUT_RECORD();
            if (pgmRtn) return;
            CMCFCNTL.FUNC = '5';
            CMCFCNTL.FUNC2 = '1';
            S000_CALL_CMZFCNTL();
            if (pgmRtn) return;
        }
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '2';
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B130_STARTBR_BY_SYSID_PROCESS() throws IOException,SQLException,Exception {
        B005_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '4';
        CMRFCNTT.BU_TYPE = CMB9902_AWA_9902.BU_TYPE;
        CMRFCNTT.SYS_ID = CMB9902_AWA_9902.SYS_ID;
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '1';
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        while (CMRFCNTT.END_FLG != 'Y') {
            B025_OUT_RECORD();
            if (pgmRtn) return;
            CMCFCNTL.FUNC = '5';
            CMCFCNTL.FUNC2 = '1';
            S000_CALL_CMZFCNTL();
            if (pgmRtn) return;
        }
        CMCFCNTL.FUNC = '5';
        CMCFCNTL.FUNC2 = '2';
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B110_READ_PROCESS() throws IOException,SQLException,Exception {
        B005_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        CMCFCNTL.FUNC = '3';
        CMRFCNTT.BU_TYPE = CMB9902_AWA_9902.BU_TYPE;
        CMRFCNTT.SYS_ID = CMB9902_AWA_9902.SYS_ID;
        S000_CALL_CMZFCNTL();
        if (pgmRtn) return;
        B025_OUT_RECORD();
        if (pgmRtn) return;
    }
    public void B005_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = WS_TS_CNTL.WS_TS_MAIN_TIT;
        SCCMPAG.MAX_COL_NO = 36;
        SCCMPAG.SUBT_ROW_CNT = WS_TS_CNTL.WS_TS_TIT_NUM;
        SCCMPAG.SCR_ROW_CNT = 20;
        B_MPAG();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_TS_CNT = 0;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B025_OUT_RECORD() throws IOException,SQLException,Exception {
        WS_OUT_DATA.WS_TS_CNT += 1;
        WS_OUT_DATA.WS_BU_TYPE = CMRFCNTT.BU_TYPE;
        WS_OUT_DATA.WS_SYS_ID = CMRFCNTT.SYS_ID;
        WS_OUT_DATA.WS_SCENE_FLG = CMRFCNTT.SCENE_FLG;
        WS_OUT_DATA.WS_FILE_IO = CMRFCNTT.FILE_IO;
        WS_OUT_DATA.WS_TASK_NO = CMRFCNTT.TASK_NO;
        WS_OUT_DATA.WS_MAX_NO = CMRFCNTT.MAX_NO;
        WS_OUT_DATA.WS_USE_NO = CMRFCNTT.USE_NO;
        WS_TS_REC = " ";
        WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        CEP.TRC(SCCGWA, CMRFCNTT.BU_TYPE);
        CEP.TRC(SCCGWA, CMRFCNTT.SYS_ID);
        CEP.TRC(SCCGWA, CMRFCNTT.TASK_NO);
        CEP.TRC(SCCGWA, CMRFCNTT.MAX_NO);
        CEP.TRC(SCCGWA, CMRFCNTT.USE_NO);
        CEP.TRC(SCCGWA, WS_TS_REC);
        WS_LEN = 36;
        if (WS_OUT_DATA.WS_TS_CNT <= K_Q_MAX_CNT) {
            S000_WRITE_TS();
            if (pgmRtn) return;
        } else {
            WS_TS_REC = " ";
            WS_TS_REC = "TO BE CONTINUED";
            WS_LEN = 36;
            S000_WRITE_TS();
            if (pgmRtn) return;
            WS_END_CNTL_FLG = 'Y';
        }
    }
    public void S000_CALL_CMZFCNTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-U-MAINTAIN-CNTL", CMCFCNTL);
        if (CMCFCNTL.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = CMCFCNTL.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = CMCFCNTL.RC.RC_RTNCODE;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
