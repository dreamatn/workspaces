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

public class VTZSVTCD {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    VTZSVTCD_WS_OUTPUT WS_OUTPUT = new VTZSVTCD_WS_OUTPUT();
    VTZSVTCD_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZSVTCD_WS_BROWSE_OUTPUT();
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
    VTCRVTCD VTCRVTCD = new VTCRVTCD();
    VTRVTCD VTRVTCD = new VTRVTCD();
    VTRPRDR VTRPRDR = new VTRPRDR();
    VTCRPRDR VTCRPRDR = new VTCRPRDR();
    VTRITMR VTRITMR = new VTRITMR();
    VTCRITMR VTCRITMR = new VTCRITMR();
    VTRACCT VTRACCT = new VTRACCT();
    VTCRACCT VTCRACCT = new VTCRACCT();
    VTRVTCD VTROVTCD = new VTRVTCD();
    VTRVTCD VTRNVTCD = new VTRVTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSVTCD VTCSVTCD;
    public void MP(SCCGWA SCCGWA, VTCSVTCD VTCSVTCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSVTCD = VTCSVTCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSVTCD return!");
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
        if (VTCSVTCD.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSVTCD.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSVTCD.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSVTCD.FUNC == 'M') {
            B050_MODIFY_RECORD();
            if (pgmRtn) return;
        } else if (VTCSVTCD.FUNC == 'D') {
            B070_DELETE_RECORD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCSVTCD.FUNC != 'B') {
            R000_VTCD_INFO_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (VTCSVTCD.FUNC == 'A') {
            if (VTCSVTCD.CODE.trim().length() == 0 
                || VTCSVTCD.CHS_NM.trim().length() == 0 
                || VTCSVTCD.EXP_DATE == 0 
                || VTCSVTCD.EFF_DATE == 0) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, VTRVTCD);
            IBS.init(SCCGWA, VTCRVTCD);
            VTRVTCD.KEY.CODE = VTCSVTCD.CODE;
            VTCRVTCD.FUNC = 'Q';
            VTCRVTCD.POINTER = VTRVTCD;
            VTCRVTCD.REC_LEN = 206;
            S000_CALL_VTZRVTCD();
            if (pgmRtn) return;
            if (VTCRVTCD.RETURN_INFO == 'F') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_CODE_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTCSVTCD.EXP_DATE <= VTCSVTCD.EFF_DATE) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_EXP_DATE_LT_EFF_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTCSVTCD.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_EFF_DATE_GT_AC_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (VTCSVTCD.FUNC == 'M') {
            if (VTCSVTCD.EXP_DATE <= VTCSVTCD.EFF_DATE) {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_EXP_DATE_LT_EFF_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (VTCSVTCD.FUNC == 'D') {
            IBS.init(SCCGWA, VTRPRDR);
            IBS.init(SCCGWA, VTCRPRDR);
            VTRPRDR.KEY.CODE = VTCSVTCD.CODE;
            VTCRPRDR.FUNC = 'B';
            VTCRPRDR.OPT = 'W';
            VTCRPRDR.POINTER = VTRPRDR;
            VTCRPRDR.REC_LEN = 179;
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
            VTCRPRDR.OPT = 'N';
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
            while (VTCRPRDR.RETURN_INFO != 'N') {
                if (VTRPRDR.STS == 'N') {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_CODE_NOT_DELETE;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                VTCRPRDR.OPT = 'N';
                S000_CALL_VTZRPRDR();
                if (pgmRtn) return;
            }
            VTCRPRDR.OPT = 'E';
            S000_CALL_VTZRPRDR();
            if (pgmRtn) return;
            IBS.init(SCCGWA, VTRACCT);
            IBS.init(SCCGWA, VTCRACCT);
            VTRACCT.KEY.CODE = VTCSVTCD.CODE;
            VTCRACCT.FUNC = 'B';
            VTCRACCT.OPT = 'C';
            VTCRACCT.POINTER = VTRACCT;
            VTCRACCT.REC_LEN = 133;
            S000_CALL_VTZRACCT();
            if (pgmRtn) return;
            if (VTCRITMR.RETURN_INFO == 'F') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_CODE_NOT_DELETE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 166;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, VTRVTCD);
        CEP.TRC(SCCGWA, VTCSVTCD.CODE);
        VTRVTCD.KEY.CODE = VTCSVTCD.CODE;
        VTCRVTCD.FUNC = 'B';
        VTCRVTCD.OPT = 'S';
        VTCRVTCD.POINTER = VTRVTCD;
        VTCRVTCD.REC_LEN = 206;
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        VTCRVTCD.OPT = 'N';
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        while (VTCRVTCD.RETURN_INFO != 'N') {
            R00_BRW_OUTPUT();
            if (pgmRtn) return;
            VTCRVTCD.OPT = 'N';
            S000_CALL_VTZRVTCD();
            if (pgmRtn) return;
        }
        VTCRVTCD.OPT = 'E';
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
    }
    public void B040_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRVTCD);
        IBS.init(SCCGWA, VTRVTCD);
        IBS.init(SCCGWA, VTROVTCD);
        IBS.init(SCCGWA, VTRNVTCD);
        CEP.TRC(SCCGWA, VTCSVTCD.CODE);
        CEP.TRC(SCCGWA, VTCSVTCD.RT);
        CEP.TRC(SCCGWA, VTCSVTCD.CHS_NM);
        CEP.TRC(SCCGWA, VTCSVTCD.ENG_NM);
        CEP.TRC(SCCGWA, VTCSVTCD.EFF_DATE);
        CEP.TRC(SCCGWA, VTCSVTCD.EXP_DATE);
        CEP.TRC(SCCGWA, VTCSVTCD.REMARK);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        VTRVTCD.KEY.CODE = VTCSVTCD.CODE;
        VTRVTCD.RT = VTCSVTCD.RT;
        VTRVTCD.CHS_NM = VTCSVTCD.CHS_NM;
        VTRVTCD.ENG_NM = VTCSVTCD.ENG_NM;
        VTRVTCD.EFF_DATE = VTCSVTCD.EFF_DATE;
        VTRVTCD.EXP_DATE = VTCSVTCD.EXP_DATE;
        VTRVTCD.RMK = VTCSVTCD.REMARK;
        VTRVTCD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRVTCD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        VTCRVTCD.FUNC = 'C';
        VTCRVTCD.POINTER = VTRVTCD;
        VTCRVTCD.REC_LEN = 206;
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_MODIFY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRVTCD);
        IBS.init(SCCGWA, VTRVTCD);
        IBS.init(SCCGWA, VTROVTCD);
        IBS.init(SCCGWA, VTRNVTCD);
        VTRVTCD.KEY.CODE = VTCSVTCD.CODE;
        VTCRVTCD.FUNC = 'R';
        VTCRVTCD.POINTER = VTRVTCD;
        VTCRVTCD.REC_LEN = 206;
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRVTCD.FUNC = 'U';
        VTRVTCD.RT = VTCSVTCD.RT;
        VTRVTCD.CHS_NM = VTCSVTCD.CHS_NM;
        VTRVTCD.ENG_NM = VTCSVTCD.ENG_NM;
        VTRVTCD.RMK = VTCSVTCD.REMARK;
        VTRVTCD.EFF_DATE = VTCSVTCD.EFF_DATE;
        VTRVTCD.EXP_DATE = VTCSVTCD.EXP_DATE;
        VTRVTCD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRVTCD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        S000_SAVE_NEW_DATA();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B070_DELETE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTCRVTCD);
        IBS.init(SCCGWA, VTRVTCD);
        IBS.init(SCCGWA, VTROVTCD);
        IBS.init(SCCGWA, VTRNVTCD);
        VTRVTCD.KEY.CODE = VTCSVTCD.CODE;
        VTRVTCD.RT = VTCSVTCD.RT;
        VTRVTCD.CHS_NM = VTCSVTCD.CHS_NM;
        VTRVTCD.ENG_NM = VTCSVTCD.ENG_NM;
        VTRVTCD.RMK = VTCSVTCD.REMARK;
        VTRVTCD.EFF_DATE = VTCSVTCD.EFF_DATE;
        VTRVTCD.EXP_DATE = VTCSVTCD.EXP_DATE;
        VTCRVTCD.FUNC = 'R';
        VTCRVTCD.POINTER = VTRVTCD;
        VTCRVTCD.REC_LEN = 206;
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        S000_SAVE_OLD_DATA();
        if (pgmRtn) return;
        VTCRVTCD.FUNC = 'D';
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        H000_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, VTRVTCD);
        IBS.init(SCCGWA, VTCRVTCD);
        VTRVTCD.KEY.CODE = VTCSVTCD.CODE;
        VTCRVTCD.FUNC = 'Q';
        VTCRVTCD.POINTER = VTRVTCD;
        VTCRVTCD.REC_LEN = 206;
        S000_CALL_VTZRVTCD();
        if (pgmRtn) return;
        if (VTCRVTCD.RETURN_INFO == 'N') {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_CODE_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_VTCD_INFO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CODE = VTRVTCD.KEY.CODE;
        WS_OUTPUT.WS_RT = VTRVTCD.RT;
        WS_OUTPUT.WS_CHS_NM = VTRVTCD.CHS_NM;
        WS_OUTPUT.WS_ENG_NM = VTRVTCD.ENG_NM;
        WS_OUTPUT.WS_REMARK = VTRVTCD.RMK;
        WS_OUTPUT.WS_EFF_DATE = VTRVTCD.EFF_DATE;
        WS_OUTPUT.WS_EXP_DATE = VTRVTCD.EXP_DATE;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CODE);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_CHS_NM);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_ENG_NM);
        SCCFMT.FMTID = "VT010";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 166;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R00_BRW_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        IBS.init(SCCGWA, WS_BROWSE_OUTPUT);
        CEP.TRC(SCCGWA, VTRVTCD.KEY.CODE);
        CEP.TRC(SCCGWA, VTRVTCD.RT);
        CEP.TRC(SCCGWA, VTRVTCD.ENG_NM);
        WS_BROWSE_OUTPUT.WS_BRW_CODE = VTRVTCD.KEY.CODE;
        WS_BROWSE_OUTPUT.WS_BRW_RT = VTRVTCD.RT;
        WS_BROWSE_OUTPUT.WS_BRW_ENG_NM = VTRVTCD.ENG_NM;
        WS_BROWSE_OUTPUT.WS_BRW_CHS_NM = VTRVTCD.CHS_NM;
        WS_BROWSE_OUTPUT.WS_BRW_REMARK = VTRVTCD.RMK;
        WS_BROWSE_OUTPUT.WS_BRW_EFF_DATE = VTRVTCD.EFF_DATE;
        WS_BROWSE_OUTPUT.WS_BRW_EXP_DATE = VTRVTCD.EXP_DATE;
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_CODE);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_CHS_NM);
        CEP.TRC(SCCGWA, WS_BROWSE_OUTPUT.WS_BRW_ENG_NM);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BROWSE_OUTPUT);
        SCCMPAG.DATA_LEN = 166;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_SAVE_NEW_DATA() throws IOException,SQLException,Exception {
        VTRNVTCD.KEY.CODE = VTCSVTCD.CODE;
        VTRNVTCD.RT = VTCSVTCD.RT;
        VTRNVTCD.CHS_NM = VTCSVTCD.CHS_NM;
        VTRNVTCD.ENG_NM = VTCSVTCD.ENG_NM;
        VTRNVTCD.EFF_DATE = VTCSVTCD.EFF_DATE;
        VTRNVTCD.EXP_DATE = VTCSVTCD.EXP_DATE;
        VTRNVTCD.RMK = VTCSVTCD.REMARK;
        VTRNVTCD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTRNVTCD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_SAVE_OLD_DATA() throws IOException,SQLException,Exception {
        VTROVTCD.KEY.CODE = VTRVTCD.KEY.CODE;
        VTROVTCD.RT = VTRVTCD.RT;
        VTROVTCD.CHS_NM = VTRVTCD.CHS_NM;
        VTROVTCD.ENG_NM = VTRVTCD.ENG_NM;
        VTROVTCD.EFF_DATE = VTRVTCD.EFF_DATE;
        VTROVTCD.EXP_DATE = VTRVTCD.EXP_DATE;
        VTROVTCD.RMK = VTRVTCD.RMK;
        VTROVTCD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTROVTCD.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void H000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (VTCSVTCD.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.TX_RMK = "ADD VTTVTCD INFO";
        } else if (VTCSVTCD.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.TX_RMK = "CHANGE VTTVTCD INFO";
        } else if (VTCSVTCD.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.TX_RMK = "DELETE VTTVTCD INFO";
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "VT010";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 166;
        BPCPNHIS.INFO.OLD_DAT_PT = VTROVTCD;
        BPCPNHIS.INFO.NEW_DAT_PT = VTRNVTCD;
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
    public void S000_CALL_VTZRVTCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-TAX-MAINTAIN", VTCRVTCD);
    }
    public void S000_CALL_VTZRPRDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-PRDR-MAINTAIN", VTCRPRDR);
    }
    public void S000_CALL_VTZRITMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-ITMR-MAINTAIN", VTCRITMR);
    }
    public void S000_CALL_VTZRACCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-R-ACCT-MAINTAIN", VTCRACCT);
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
