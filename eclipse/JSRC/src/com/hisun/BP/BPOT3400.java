package com.hisun.BP;

import com.hisun.SM.*;
import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3400 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short K_SUBR_ROW_CNT = 0;
    short K_MAX_COL_NO = 500;
    short K_SCR_ROW_NO = 24;
    short K_SCR_COL_CNT = 16;
    short K_MAX_BUTT_CNT = 9;
    BPOT3400_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT3400_WS_TEMP_VARIABLE();
    BPOT3400_WS_TLPM_HEAD WS_TLPM_HEAD = new BPOT3400_WS_TLPM_HEAD();
    BPOT3400_WS_TLPM_DATA WS_TLPM_DATA = new BPOT3400_WS_TLPM_DATA();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    AIRHIS AIRHIS = new AIRHIS();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCRBBTL SCCRBBTL = new SCCRBBTL();
    SCRBBTL SCRBBTL = new SCRBBTL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCFITYP BPCFITYP = new BPCFITYP();
    SCCGWA SCCGWA;
    BPB3400_AWA_3400 BPB3400_AWA_3400;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT3400 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3400_AWA_3400>");
        BPB3400_AWA_3400 = (BPB3400_AWA_3400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        if (BPB3400_AWA_3400.FITYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFITYP);
            IBS.init(SCCGWA, BPCPRMR);
            BPCFITYP.KEY.TYP = "FITYP";
            BPCFITYP.KEY.CD = BPB3400_AWA_3400.FITYP;
            BPCPRMR.DAT_PTR = BPCFITYP;
            IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
            CEP.TRC(SCCGWA, BPCPRMR.RC);
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
            if (BPB3400_AWA_3400.SERVCODE.trim().length() == 0) {
                BPB3400_AWA_3400.SERVCODE = BPCFITYP.DATA_TXT.BSP_SERV_CODE;
            }
        }
        IBS.init(SCCGWA, WS_TLPM_HEAD);
        WS_TLPM_HEAD.WS_FILE_TYPE = BPB3400_AWA_3400.FITYP;
        WS_TLPM_HEAD.WS_BSP_SVR_CODE = BPB3400_AWA_3400.SERVCODE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_TLPM_HEAD);
        SCCMPAG.DATA_LEN = 15;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCRBBTL);
        IBS.init(SCCGWA, SCRBBTL);
        SCCRBBTL.PTR = SCRBBTL;
        SCCRBBTL.LEN = 837;
        if (BPB3400_AWA_3400.SERVCODE.trim().length() > 0) {
            SCCRBBTL.FUN = 'V';
            SCRBBTL.KEY.SERV_CODE = BPB3400_AWA_3400.SERVCODE;
        } else {
            if (BPB3400_AWA_3400.AP_TYPE.trim().length() > 0) {
                if (BPB3400_AWA_3400.BAT_SEQ > 0) {
                    SCRBBTL.KEY.AP_TYPE = BPB3400_AWA_3400.AP_TYPE;
                    SCRBBTL.KEY.AP_BATNO = BPB3400_AWA_3400.BAT_SEQ;
                    SCCRBBTL.FUN = 'B';
                } else {
                    SCRBBTL.KEY.AP_TYPE = BPB3400_AWA_3400.AP_TYPE;
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
            IBS.init(SCCGWA, WS_TLPM_DATA);
            WS_TEMP_VARIABLE.WS_TS_REC = " ";
            WS_TLPM_DATA.WS_AP_TYPE = SCRBBTL.KEY.AP_TYPE;
            WS_TLPM_DATA.WS_BAT_SEQ = SCRBBTL.KEY.AP_BATNO;
            WS_TLPM_DATA.WS_RCV_DATE = SCRBBTL.RCV_DATE;
            WS_TLPM_DATA.WS_FIN_AC_DATE = SCRBBTL.FIN_AC_DATE;
            WS_TLPM_DATA.WS_GEN_PROC_STS = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[1-1].STEP_STS;
            WS_TLPM_DATA.WS_BSP_PROC_STS = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[2-1].STEP_STS;
            WS_TLPM_DATA.WS_BAT_PROC_STS = SCRBBTL.REDEFINES28.REDEFINES30.STEP_CTL[3-1].STEP_STS;
            WS_TEMP_VARIABLE.WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_TLPM_DATA);
            WS_TEMP_VARIABLE.WS_LEN = 37;
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
        SCCMPAG.MAX_COL_NO = 37;
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
