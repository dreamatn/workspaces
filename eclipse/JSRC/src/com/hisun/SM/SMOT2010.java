package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT2010 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_SUBR_ROW_CNT = 0;
    short K_MAX_COL_NO = 500;
    short K_SCR_ROW_NO = 24;
    short K_SCR_COL_CNT = 16;
    short K_MAX_BUTT_CNT = 9;
    SMOT2010_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT2010_WS_TEMP_VARIABLE();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCRBBTL SCCRBBTL = new SCCRBBTL();
    SCRBBTL SCRBBTL = new SCRBBTL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SMCC12 SMCC12 = new SMCC12();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SMB2010_AWA_2010 SMB2010_AWA_2010;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT2010 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB2010_AWA_2010>");
        SMB2010_AWA_2010 = (SMB2010_AWA_2010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        B280_OUTPUT_QULIST_HEAD();
        if (pgmRtn) return;
        B002_INQ_RECORD();
        if (pgmRtn) return;
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B002_INQ_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCRBBTL);
        IBS.init(SCCGWA, SCRBBTL);
        SCCRBBTL.PTR = SCRBBTL;
        SCCRBBTL.LEN = 837;
        if (SMB2010_AWA_2010.SERVCODE.trim().length() > 0) {
            SCCRBBTL.FUN = 'V';
            SCRBBTL.KEY.SERV_CODE = SMB2010_AWA_2010.SERVCODE;
        } else {
            if (SMB2010_AWA_2010.AP_TYPE.trim().length() > 0) {
                if (SMB2010_AWA_2010.BAT_SEQ > 0) {
                    SCRBBTL.KEY.AP_TYPE = SMB2010_AWA_2010.AP_TYPE;
                    SCRBBTL.KEY.AP_BATNO = SMB2010_AWA_2010.BAT_SEQ;
                    SCCRBBTL.FUN = 'B';
                } else {
                    SCRBBTL.KEY.AP_TYPE = SMB2010_AWA_2010.AP_TYPE;
                    SCCRBBTL.FUN = 'A';
                }
            } else {
                IBS.init(SCCGWA, SCRBBTL);
                SCCRBBTL.FUN = 'S';
            }
        }
        CEP.TRC(SCCGWA, SCRBBTL.KEY);
        S000_CALL_SCZRBBTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCRBBTL.RETURN_INFO);
        SCCRBBTL.FUN = 'R';
        S000_CALL_SCZRBBTL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCRBBTL.RETURN_INFO);
        while (SCCRBBTL.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, SMCC12);
            WS_TEMP_VARIABLE.WS_TS_REC = " ";
            SMCC12.SERV_CODE = SCRBBTL.KEY.SERV_CODE;
            SMCC12.AP_TYPE = SCRBBTL.KEY.AP_TYPE;
            SMCC12.BAT_SEQ = SCRBBTL.KEY.AP_BATNO;
            SMCC12.RCV_DATE = SCRBBTL.RCV_DATE;
            SMCC12.FIN_AC_DATE = SCRBBTL.FIN_AC_DATE;
            SMCC12.BSP_ST_TIME = SCRBBTL.START_TIME;
            SMCC12.BSP_EN_TIME = SCRBBTL.FIN_TIME;
            SMCC12.CHK_CNT = SCRBBTL.CHK_CNT;
            SMCC12.ERR_CNT = SCRBBTL.ERR_CNT;
            SMCC12.STEP = SCRBBTL.STEP_NUM;
            SMCC12.GEN_PROC_STS = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_STS;
            SMCC12.GEN_PROC = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_PROC_NAME;
            SMCC12.BSP_PROC_STS = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_STS;
            SMCC12.BSP_PROC = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_PROC_NAME;
            SMCC12.BAT_PROC_STS = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_STS;
            SMCC12.BAT_PROC = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_PROC_NAME;
            WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, SMCC12);
            WS_TEMP_VARIABLE.WS_LEN = 109;
            S000_WRITE_TS();
            if (pgmRtn) return;
            S000_CALL_SCZRBBTL();
            if (pgmRtn) return;
        }
        SCCRBBTL.FUN = 'E';
        S000_CALL_SCZRBBTL();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCZRBBTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-R-BRW-BBTL", SCCRBBTL);
        if (SCCRBBTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRBBTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B280_OUTPUT_QULIST_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = K_SUBR_ROW_CNT;
        SCCMPAG.MAX_COL_NO = K_MAX_COL_NO;
        SCCMPAG.SCR_ROW_CNT = K_SCR_ROW_NO;
        SCCMPAG.SCR_COL_CNT = K_SCR_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TEMP_VARIABLE.WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_TEMP_VARIABLE.WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
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
