package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSAMTL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "IBAC CODE INFO MAINTAIN";
    String K_CPY_BPRPAMTL = "BPRPAMTL";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSAMTL_WS_KEY WS_KEY = new BPZSAMTL_WS_KEY();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPAMTL BPRPAMTL = new BPRPAMTL();
    BPRPAMTL BPROAMTL = new BPRPAMTL();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCOAMTO BPCOAMTO = new BPCOAMTO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSAMTL BPCSAMTL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSAMTL BPCSAMTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSAMTL = BPCSAMTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSAMTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSAMTL.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSAMTL.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSAMTL.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSAMTL.FUNC == 'D') {
            B060_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCSAMTL.FUNC);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPAMTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPAMTL.KEY.TYP = "AMTL";
        WS_KEY.WS_BR = BPCSAMTL.BR;
        WS_KEY.WS_MACH_NO = BPCSAMTL.MACH_NO;
        BPCPRMM.EFF_DT = BPCSAMTL.EFF_DATE;
        BPRPAMTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPAMTL;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MACHTLR_NOFIND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCSAMTL.TLR_NO = BPRPAMTL.DATA_TXT.TLR_NO;
        CEP.TRC(SCCGWA, BPCSAMTL.TLR_NO);
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPAMTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        BPCPRMM.EFF_DT = BPCSAMTL.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSAMTL.EXP_DATE;
        WS_KEY.WS_BR = BPCSAMTL.BR;
        WS_KEY.WS_MACH_NO = BPCSAMTL.MACH_NO;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRPAMTL.KEY.TYP = "AMTL";
        BPRPAMTL.DATA_LEN = 40;
        BPCPRMM.DAT_PTR = BPRPAMTL;
        CEP.TRC(SCCGWA, BPRPAMTL.DATA_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPAMTL;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPAMTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPCPRMM.EFF_DT = BPCSAMTL.EFF_DATE;
        WS_KEY.WS_BR = BPCSAMTL.BR;
        WS_KEY.WS_MACH_NO = BPCSAMTL.MACH_NO;
        BPRPAMTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        BPRPAMTL.KEY.TYP = "AMTL";
        BPCPRMM.DAT_PTR = BPRPAMTL;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MACHTLR_NOFIND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCPRMM.TS.equalsIgnoreCase("Y") 
            && BPCSAMTL.EFF_DATE != BPCPRMM.EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARAMETER_EFFECTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRPAMTL, BPROAMTL);
        BPCPRMM.EFF_DT = BPCSAMTL.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSAMTL.EXP_DATE;
        WS_KEY.WS_BR = BPCSAMTL.BR;
        WS_KEY.WS_MACH_NO = BPCSAMTL.MACH_NO;
        BPRPAMTL.KEY.TYP = "AMTL";
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '2';
        BPRPAMTL.DATA_LEN = 40;
        BPCPRMM.DAT_PTR = BPRPAMTL;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPAMTL;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROAMTL;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRPAMTL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSAMTL.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOAMTO;
        SCCFMT.DATA_LEN = 172;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPAMTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_KEY.WS_BR = BPCSAMTL.BR;
        WS_KEY.WS_MACH_NO = BPCSAMTL.MACH_NO;
        BPRPAMTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        BPCPRMM.EFF_DT = BPCSAMTL.EFF_DATE;
        BPRPAMTL.KEY.TYP = "AMTL";
        BPCPRMM.DAT_PTR = BPRPAMTL;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MACHTLR_NOFIND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCPRMM.TS.equalsIgnoreCase("Y")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARAMETER_EFFECTIVE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_KEY.WS_BR = BPCSAMTL.BR;
        WS_KEY.WS_MACH_NO = BPCSAMTL.MACH_NO;
        BPRPAMTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        BPRPAMTL.KEY.TYP = "AMTL";
        BPCPRMM.FUNC = '1';
        BPRPAMTL.DATA_LEN = 40;
        BPCPRMM.DAT_PTR = BPRPAMTL;
        B060_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B060_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPAMTL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRPAMTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_KEY);
        BPRPAMTL.DATA_TXT.TLR_NO = BPCSAMTL.TLR_NO;
        BPRPAMTL.DATA_TXT.EFF_DT = BPCSAMTL.EFF_DATE;
        BPRPAMTL.DATA_TXT.EXP_DT = BPCSAMTL.EXP_DATE;
        BPRPAMTL.DATA_TXT.UPD_DT = BPCSAMTL.UPD_DT;
        BPRPAMTL.DATA_TXT.UPD_TLR = BPCSAMTL.UPD_TLR;
        CEP.TRC(SCCGWA, "DATA-PARAMETER123");
        CEP.TRC(SCCGWA, BPRPAMTL.DATA_TXT.TLR_NO);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        BPCOAMTO.FUNC = BPCSAMTL.FUNC;
        BPCOAMTO.PARM_TP = BPRPAMTL.KEY.TYP;
        IBS.CPY2CLS(SCCGWA, BPRPAMTL.KEY.CD, WS_KEY);
        BPCOAMTO.BR = WS_KEY.WS_BR;
        BPCOAMTO.MACH_NO = WS_KEY.WS_MACH_NO;
        BPCOAMTO.TLR_NO = BPRPAMTL.DATA_TXT.TLR_NO;
        BPCOAMTO.EFF_DATE = BPCPRMM.EFF_DT;
        BPCOAMTO.EXP_DATE = BPCPRMM.EXP_DT;
        BPCOAMTO.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOAMTO.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
