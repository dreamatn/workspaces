package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSRNUM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP200";
    String K_OUTPUT_MPAG = "SCZ01";
    String K_R_DRW_RNUM = "BP-R-DRW-RNUM";
    String K_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_CCY_INQUIRE = "BP-INQUIRE-CCY";
    String K_HIS_REMARKS = "DEVIATION MAINTENANCE";
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    String K_HIS_COPYBOOK_NAME = "BPCHRNUM";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    BPZSRNUM_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSRNUM_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCHRNUM BPCORNUM = new BPCHRNUM();
    BPCHRNUM BPCNRNUM = new BPCHRNUM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCRRNUM BPCRRNUM = new BPCRRNUM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRRNUM BPRRNUM = new BPRRNUM();
    SCCGWA SCCGWA;
    BPCSRNUM BPCSRNUM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSRNUM BPCSRNUM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSRNUM = BPCSRNUM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-START ");
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-END   ");
        CEP.TRC(SCCGWA, "BPZSRNUM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSRNUM.RC);
        IBS.init(SCCGWA, BPRRNUM);
        IBS.init(SCCGWA, BPCRRNUM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B100 START");
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B200 END");
        if (BPCSRNUM.FUNC == 'Q'
            || BPCSRNUM.FUNC == 'C'
            || BPCSRNUM.FUNC == 'U'
            || BPCSRNUM.FUNC == 'D') {
            B200_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSRNUM.FUNC == 'B') {
            B300_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCSRNUM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSRNUM.BR == ' ' 
            && BPCSRNUM.FUNC != 'B') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_BR, BPCSRNUM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRRNUM.FUNC = 'Q';
        BPRRNUM.KEY.BR = BPCSRNUM.BR;
        BPRRNUM.KEY.CCY = BPCSRNUM.CCY;
        BPRRNUM.CRNUM = BPCSRNUM.CRNUM;
        CEP.TRC(SCCGWA, BPCSRNUM.BR);
        CEP.TRC(SCCGWA, BPCSRNUM.CCY);
        CEP.TRC(SCCGWA, BPRRNUM.KEY.BR);
        CEP.TRC(SCCGWA, BPRRNUM.KEY.CCY);
        S000_CALL_BPZRRNUM();
        if (pgmRtn) return;
        if (BPCRRNUM.RETURN_INFO == 'N') {
            if (BPCSRNUM.FUNC == 'Q' 
                || BPCSRNUM.FUNC == 'U' 
                || BPCSRNUM.FUNC == 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCSRNUM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (BPCSRNUM.FUNC == 'C') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST, BPCSRNUM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSRNUM.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCSRNUM.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (BPCSRNUM.CCY.trim().length() > 0) {
            R000_CCY_CHECK();
            if (pgmRtn) return;
        }
    }
    public void B200_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRRNUM);
        IBS.init(SCCGWA, BPRRNUM);
        if (BPCSRNUM.FUNC == 'Q') {
            BPCRRNUM.FUNC = 'Q';
        } else if (BPCSRNUM.FUNC == 'C') {
            BPCRRNUM.FUNC = 'A';
        } else if (BPCSRNUM.FUNC == 'U'
            || BPCSRNUM.FUNC == 'D') {
            BPCRRNUM.FUNC = 'M';
        }
        BPRRNUM.KEY.BR = BPCSRNUM.BR;
        BPRRNUM.KEY.CCY = BPCSRNUM.CCY;
        BPRRNUM.CRNUM = BPCSRNUM.CRNUM;
        CEP.TRC(SCCGWA, BPCSRNUM.BR);
        CEP.TRC(SCCGWA, BPCSRNUM.CCY);
        CEP.TRC(SCCGWA, BPCSRNUM.CRNUM);
        S000_CALL_BPZRRNUM();
        if (pgmRtn) return;
        R000_OLD_DAT_PROCESS();
        if (pgmRtn) return;
        if (BPCSRNUM.FUNC == 'C' 
            || BPCSRNUM.FUNC == 'U' 
            || BPCSRNUM.FUNC == 'D') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSRNUM.FUNC == 'D') {
            BPCRRNUM.FUNC = 'D';
            S000_CALL_BPZRRNUM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSRNUM.BR);
        CEP.TRC(SCCGWA, BPCSRNUM.CCY);
        CEP.TRC(SCCGWA, BPCSRNUM.CRNUM);
        BPRRNUM.KEY.BR = BPCSRNUM.BR;
        BPRRNUM.KEY.CCY = BPCSRNUM.CCY;
        BPRRNUM.CRNUM = BPCSRNUM.CRNUM;
        if (BPCSRNUM.FUNC == 'U') {
            BPCRRNUM.FUNC = 'U';
            S000_CALL_BPZRRNUM();
            if (pgmRtn) return;
        }
        R000_FMT_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B300_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B500_OUT_TITLE();
        if (pgmRtn) return;
        B400_INPUT_DATA();
        if (pgmRtn) return;
        BPCRRNUM.FUNC = 'S';
        S000_CALL_BPZRRNUM();
        if (pgmRtn) return;
        BPCRRNUM.FUNC = 'R';
        S000_CALL_BPZRRNUM();
        if (pgmRtn) return;
        while (BPCRRNUM.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_MPAG_OUTPUT_DATA();
            if (pgmRtn) return;
            BPCRRNUM.FUNC = 'R';
            S000_CALL_BPZRRNUM();
            if (pgmRtn) return;
        }
        BPCRRNUM.FUNC = 'E';
        S000_CALL_BPZRRNUM();
        if (pgmRtn) return;
    }
    public void B400_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSRNUM.BR);
        CEP.TRC(SCCGWA, BPCSRNUM.CCY);
        CEP.TRC(SCCGWA, BPCSRNUM.CRNUM);
        if ((BPCSRNUM.BR == 0 
                && BPCSRNUM.CCY.trim().length() == 0 
                && BPCSRNUM.CRNUM.trim().length() == 0)) {
            BPCRRNUM.OPT = '1';
        } else if ((BPCSRNUM.BR != 0 
                && BPCSRNUM.CCY.trim().length() == 0 
                && BPCSRNUM.CRNUM.trim().length() == 0)) {
            BPCRRNUM.OPT = '2';
        } else if ((BPCSRNUM.BR == 0 
                && BPCSRNUM.CCY.trim().length() > 0 
                && BPCSRNUM.CRNUM.trim().length() == 0)) {
            BPCRRNUM.OPT = '3';
        } else if ((BPCSRNUM.BR == 0 
                && BPCSRNUM.CCY.trim().length() == 0 
                && BPCSRNUM.CRNUM.trim().length() > 0)) {
            BPCRRNUM.OPT = '4';
        } else if ((BPCSRNUM.BR != 0 
                && BPCSRNUM.CCY.trim().length() > 0 
                && BPCSRNUM.CRNUM.trim().length() == 0)) {
            BPCRRNUM.OPT = '5';
        } else if ((BPCSRNUM.BR != 0 
                && BPCSRNUM.CCY.trim().length() == 0 
                && BPCSRNUM.CRNUM.trim().length() > 0)) {
            BPCRRNUM.OPT = '6';
        } else if ((BPCSRNUM.BR == 0 
                && BPCSRNUM.CCY.trim().length() > 0 
                && BPCSRNUM.CRNUM.trim().length() > 0)) {
            BPCRRNUM.OPT = '7';
        } else if ((BPCSRNUM.BR != 0 
                && BPCSRNUM.CCY.trim().length() > 0 
                && BPCSRNUM.CRNUM.trim().length() > 0)) {
            BPCRRNUM.OPT = '8';
        }
    }
    public void B500_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 24;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 24;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_MPAG_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 24;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRRNUM.KEY.BR);
        CEP.TRC(SCCGWA, BPRRNUM.KEY.CCY);
        CEP.TRC(SCCGWA, BPRRNUM.CRNUM);
        WS_OUTPUT_DATA.WS_BR = BPRRNUM.KEY.BR;
        WS_OUTPUT_DATA.WS_CCY = BPRRNUM.KEY.CCY;
        WS_OUTPUT_DATA.WS_CRNUM = BPRRNUM.CRNUM;
    }
    public void R000_OLD_DAT_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSRNUM.FUNC == 'U') {
            IBS.init(SCCGWA, BPCORNUM);
            BPCORNUM.BR = BPCSRNUM.BR;
            BPCORNUM.CCY = BPCSRNUM.CCY;
            BPCORNUM.CRNUM = BPCSRNUM.CRNUM;
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCRRNUM.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSRNUM.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSRNUM.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.REF_NO = "" + BPCSRNUM.BR;
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO = "0" + BPCPNHIS.INFO.REF_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        if (BPCSRNUM.FUNC == 'U') {
            BPCPNHIS.INFO.OLD_DAT_PT = BPCORNUM;
            IBS.init(SCCGWA, BPCNRNUM);
            BPCNRNUM.BR = BPCSRNUM.BR;
            BPCNRNUM.CCY = BPCSRNUM.CCY;
            BPCNRNUM.CRNUM = BPCSRNUM.CRNUM;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNRNUM;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRRNUM() throws IOException,SQLException,Exception {
        BPCRRNUM.POINTER = BPRRNUM;
        BPCRRNUM.LEN = 50;
        IBS.CALLCPN(SCCGWA, K_R_DRW_RNUM, BPCRRNUM);
        if (BPCRRNUM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRRNUM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSRNUM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSRNUM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSRNUM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CCY_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPCSRNUM.CCY;
        IBS.CALLCPN(SCCGWA, CPN_CCY_INQUIRE, BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.RC.RTNCODE);
        if (BPCQCCY.RC.RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND, BPCSRNUM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSRNUM.RC);
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
