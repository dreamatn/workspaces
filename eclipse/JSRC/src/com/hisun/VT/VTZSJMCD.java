package com.hisun.VT;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class VTZSJMCD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    VTZSJMCD_WS_OUTPUT WS_OUTPUT = new VTZSJMCD_WS_OUTPUT();
    VTZSJMCD_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZSJMCD_WS_BROWSE_OUTPUT();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    VTCRJMCD VTCRJMCD = new VTCRJMCD();
    VTRJMCD VTRJMCD = new VTRJMCD();
    VTRJMDR VTRJMDR = new VTRJMDR();
    VTCRJMDR VTCRJMDR = new VTCRJMDR();
    VTRJMCD VTROJMCD = new VTRJMCD();
    VTRJMCD VTRNJMCD = new VTRJMCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSJMCD VTCSJMCD;
    public void MP(SCCGWA SCCGWA, VTCSJMCD VTCSJMCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSJMCD = VTCSJMCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSJMCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (VTCSJMCD.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSJMCD.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSJMCD.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSJMCD.FUNC == 'M') {
            B050_MODIFY_RECORD();
            if (pgmRtn) return;
        } else if (VTCSJMCD.FUNC == 'D') {
            B070_DELETE_RECORD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCSJMCD.FUNC != 'B') {
            R000_JMCD_INFO_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (VTCSJMCD.FUNC == 'A') {
            CEP.TRC(SCCGWA, VTCSJMCD.CODE);
            CEP.TRC(SCCGWA, VTCSJMCD.CHS_NM);
            CEP.TRC(SCCGWA, VTCSJMCD.JM_TYPE);
            CEP.TRC(SCCGWA, VTCSJMCD.JM_TYPE);
            CEP.TRC(SCCGWA, VTCSJMCD.JM_RAT);
            CEP.TRC(SCCGWA, VTCSJMCD.JM_AMT);
            if (VTCSJMCD.CODE.trim().length() == 0 
                || VTCSJMCD.CHS_NM.trim().length() == 0 
                || VTCSJMCD.JM_TYPE == ' ' 
                || (VTCSJMCD.JM_TYPE == '1' 
                && VTCSJMCD.JM_RAT == 0) 
                || (VTCSJMCD.JM_TYPE == '2' 
                && VTCSJMCD.JM_AMT == 0)) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, VTRJMCD);
            IBS.init(SCCGWA, VTCRJMCD);
            VTRJMCD.KEY.JM_CODE = VTCSJMCD.CODE;
            VTCRJMCD.FUNC = 'Q';
            VTCRJMCD.POINTER = VTRJMCD;
            VTCRJMCD.REC_LEN = 221;
            S000_CALL_VTZRJMCD();
            if (pgmRtn) return;
            if (VTCRJMCD.RETURN_INFO == 'F') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_JM_CODE_ALR_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (VTCSJMCD.FUNC == 'M') {
            if (VTCSJMCD.CODE.trim().length() == 0 
                || VTCSJMCD.CHS_NM.trim().length() == 0 
                || VTCSJMCD.JM_TYPE == ' ' 
                || (VTCSJMCD.JM_TYPE == '1' 
                && VTCSJMCD.JM_RAT == 0) 
                || (VTCSJMCD.JM_TYPE == '2' 
                && VTCSJMCD.JM_AMT == 0)) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (VTCSJMCD.FUNC == 'D') {
            IBS.init(SCCGWA, VTRJMDR);
            IBS.init(SCCGWA, VTCRJMDR);
            VTRJMDR.JM_CODE = VTCSJMCD.CODE;
            VTCRJMCD.FUNC = 'Q';
            VTCRJMDR.POINTER = VTRJMDR;
            VTCRJMDR.REC_LEN = 218;
            S000_CALL_VTZRJMDR();
            if (pgmRtn) return;
            if (VTCRJMCD.RETURN_INFO == 'N') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_JM_CODE_NOT_FOUND;
            }
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 110;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTRJMCD);
        CEP.TRC(SCCGWA, VTCSJMCD.CODE);
        VTRJMCD.KEY.JM_CODE = VTCSJMCD.CODE;
        CEP.TRC(SCCGWA, VTRJMCD.KEY.JM_CODE);
        VTCRJMCD.FUNC = 'B';
        VTCRJMCD.OPT = 'S';
        VTCRJMCD.POINTER = VTRJMCD;
        VTCRJMCD.REC_LEN = 221;
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
        VTCRJMCD.OPT = 'N';
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
        while (VTCRJMCD.RETURN_INFO != 'N') {
            R00_BRW_OUTPUT();
            if (pgmRtn) return;
            VTCRJMCD.OPT = 'N';
            S000_CALL_VTZRJMCD();
            if (pgmRtn) return;
        }
        VTCRJMCD.OPT = 'E';
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRJMCD);
        IBS.init(SCCGWA, VTRJMCD);
        IBS.init(SCCGWA, VTROJMCD);
        IBS.init(SCCGWA, VTRNJMCD);
        CEP.TRC(SCCGWA, VTCSJMCD.CODE);
        CEP.TRC(SCCGWA, VTCSJMCD.CHS_NM);
        CEP.TRC(SCCGWA, VTCSJMCD.ENG_NM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        VTRJMCD.KEY.JM_CODE = VTCSJMCD.CODE;
        VTRJMCD.CHS_NM = VTCSJMCD.CHS_NM;
        VTRJMCD.ENG_NM = VTCSJMCD.ENG_NM;
        VTRJMCD.JM_TYPE = VTCSJMCD.JM_TYPE;
        VTRJMCD.JM_AMT = VTCSJMCD.JM_AMT;
        VTRJMCD.JM_RAT = (double)VTCSJMCD.JM_RAT;
        VTRJMCD.BASE_IRAT_RT = VTCSJMCD.BASE_IRAT_RT;
        VTRJMCD.RMK = VTCSJMCD.RMK;
        VTRJMCD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRJMCD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTRJMCD.KEY.STS = 'N';
        VTCRJMCD.FUNC = 'C';
        VTCRJMCD.POINTER = VTRJMCD;
        VTCRJMCD.REC_LEN = 221;
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRJMCD);
        IBS.init(SCCGWA, VTRJMCD);
        IBS.init(SCCGWA, VTROJMCD);
        IBS.init(SCCGWA, VTRNJMCD);
        VTRJMCD.KEY.JM_CODE = VTCSJMCD.CODE;
        CEP.TRC(SCCGWA, VTRJMCD.KEY.JM_CODE);
        VTCRJMCD.FUNC = 'R';
        VTCRJMCD.POINTER = VTRJMCD;
        VTCRJMCD.REC_LEN = 221;
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRJMCD.FUNC = 'U';
        CEP.TRC(SCCGWA, VTCSJMCD.CHS_NM);
        CEP.TRC(SCCGWA, VTCSJMCD.ENG_NM);
        CEP.TRC(SCCGWA, VTCSJMCD.JM_TYPE);
        CEP.TRC(SCCGWA, VTCSJMCD.JM_AMT);
        CEP.TRC(SCCGWA, VTCSJMCD.JM_RAT);
        CEP.TRC(SCCGWA, VTCSJMCD.RMK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        VTRJMCD.CHS_NM = VTCSJMCD.CHS_NM;
        VTRJMCD.ENG_NM = VTCSJMCD.ENG_NM;
        VTRJMCD.JM_TYPE = VTCSJMCD.JM_TYPE;
        VTRJMCD.JM_AMT = VTCSJMCD.JM_AMT;
        VTRJMCD.JM_RAT = (double)VTCSJMCD.JM_RAT;
        VTRJMCD.RMK = VTCSJMCD.RMK;
        VTRJMCD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRJMCD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, VTRJMCD.CHS_NM);
        CEP.TRC(SCCGWA, VTRJMCD.ENG_NM);
        CEP.TRC(SCCGWA, VTRJMCD.JM_TYPE);
        CEP.TRC(SCCGWA, VTRJMCD.JM_AMT);
        CEP.TRC(SCCGWA, VTRJMCD.JM_RAT);
        CEP.TRC(SCCGWA, VTRJMCD.RMK);
        CEP.TRC(SCCGWA, VTRJMCD.UPD_DATE);
        CEP.TRC(SCCGWA, VTRJMCD.UPD_TLR);
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B070_DELETE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRJMCD);
        IBS.init(SCCGWA, VTRJMCD);
        IBS.init(SCCGWA, VTROJMCD);
        IBS.init(SCCGWA, VTRNJMCD);
        VTRJMCD.KEY.JM_CODE = VTCSJMCD.CODE;
        VTCRJMCD.FUNC = 'R';
        VTCRJMCD.POINTER = VTRJMCD;
        VTCRJMCD.REC_LEN = 221;
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRJMCD.FUNC = 'D';
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRJMCD);
        IBS.init(SCCGWA, VTCRJMCD);
        CEP.TRC(SCCGWA, VTCSJMCD.CODE);
        VTRJMCD.KEY.JM_CODE = VTCSJMCD.CODE;
        CEP.TRC(SCCGWA, VTRJMCD.KEY.JM_CODE);
        VTCRJMCD.FUNC = 'Q';
        VTCRJMCD.POINTER = VTRJMCD;
        VTCRJMCD.REC_LEN = 221;
        S000_CALL_VTZRJMCD();
        if (pgmRtn) return;
        if (VTCRJMCD.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_CODE_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_JMCD_INFO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_JM_CODE = VTRJMCD.KEY.JM_CODE;
        WS_OUTPUT.WS_CHS_NM = VTRJMCD.CHS_NM;
        WS_OUTPUT.WS_ENG_NM = VTRJMCD.ENG_NM;
        WS_OUTPUT.WS_JM_TYPE = VTRJMCD.JM_TYPE;
        WS_OUTPUT.WS_JM_AMT = VTRJMCD.JM_AMT;
        WS_OUTPUT.WS_JM_RAT = (short) VTRJMCD.JM_RAT;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_JM_CODE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CHS_NM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_ENG_NM);
        SCCFMT.FMTID = "VT020";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 171;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        CEP.TRC(SCCGWA, VTRJMCD.KEY.JM_CODE);
        CEP.TRC(SCCGWA, VTRJMCD.ENG_NM);
        WS_BROWSE_OUTPUT.WS_BRW_JM_CODE = VTRJMCD.KEY.JM_CODE;
        WS_BROWSE_OUTPUT.WS_BRW_CHS_NM = VTRJMCD.CHS_NM;
        WS_BROWSE_OUTPUT.WS_BRW_ENG_NM = VTRJMCD.ENG_NM;
        WS_BROWSE_OUTPUT.WS_BRW_JM_TYPE = VTRJMCD.JM_TYPE;
        WS_BROWSE_OUTPUT.WS_BRW_JM_AMT = VTRJMCD.JM_AMT;
        WS_BROWSE_OUTPUT.WS_BRW_JM_RAT = (short) VTRJMCD.JM_RAT;
        WS_BROWSE_OUTPUT.WS_BRW_JM_STS = VTRJMCD.KEY.STS;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_JM_CODE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_CHS_NM);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_ENG_NM);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 110;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_SAVE_NEW_DATA() throws IOException,SQLException,Exception {
        VTRNJMCD.KEY.JM_CODE = VTCSJMCD.CODE;
        VTRNJMCD.CHS_NM = VTCSJMCD.CHS_NM;
        VTRNJMCD.ENG_NM = VTCSJMCD.ENG_NM;
        VTRNJMCD.JM_TYPE = VTCSJMCD.JM_TYPE;
        VTRNJMCD.JM_AMT = VTCSJMCD.JM_AMT;
        VTRNJMCD.JM_RAT = (double)VTCSJMCD.JM_RAT;
    }
    public void S000_SAVE_OLD_DATA() throws IOException,SQLException,Exception {
        VTROJMCD.KEY.JM_CODE = VTCSJMCD.CODE;
        VTROJMCD.CHS_NM = VTCSJMCD.CHS_NM;
        VTROJMCD.ENG_NM = VTCSJMCD.ENG_NM;
        VTROJMCD.JM_TYPE = VTCSJMCD.JM_TYPE;
        VTROJMCD.JM_AMT = VTCSJMCD.JM_AMT;
        VTROJMCD.JM_RAT = (double)VTCSJMCD.JM_RAT;
    }
    public void H000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (VTCSJMCD.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD VTTJMCD INFO";
        } else if (VTCSJMCD.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "CHANGE VTTJMCD INFO";
        } else if (VTCSJMCD.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.TX_RMK = "DELETE VTTJMCD INFO";
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "VT020";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 171;
        BPCPNHIS.INFO.OLD_DAT_PT = VTROJMCD;
        BPCPNHIS.INFO.NEW_DAT_PT = VTRNJMCD;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_VTZRJMCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-JMCD-MAINTAIN", VTCRJMCD);
    }
    public void S000_CALL_VTZRJMDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-JMDR-MAINTAIN", VTCRJMDR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
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
