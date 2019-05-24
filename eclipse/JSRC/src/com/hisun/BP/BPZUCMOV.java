package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUCMOV {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_ADW_CMOV = "BP-R-ADW-CMOV       ";
    String CPN_R_ADW_MPAR = "BP-R-ADW-MPAR       ";
    String CPN_R_ADW_CHIS = "BP-R-ADW-CHIS       ";
    String CPN_R_ADW_HPAR = "BP-R-ADW-HPAR       ";
    String CPN_P_VCH_CPNT = "BP-P-VWA-WRITE";
    String CPN_P_CASH_PROD_INQ = "BP-P-CASH-PROD-INQ";
    int WS_INDEX = 0;
    BPZUCMOV_WS_RGNC_KEY WS_RGNC_KEY = new BPZUCMOV_WS_RGNC_KEY();
    BPZUCMOV_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZUCMOV_WS_EWA_AC_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPRMPAR BPRMPAR = new BPRMPAR();
    BPRCHIS BPRCHIS = new BPRCHIS();
    BPRHPAR BPRHPAR = new BPRHPAR();
    BPCRPARF BPCRPARF = new BPCRPARF();
    BPCRMOVF BPCRMOVF = new BPCRMOVF();
    BPCRHPAR BPCRHPAR = new BPCRHPAR();
    BPCRCHIS BPCRCHIS = new BPCRCHIS();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    SCCGWA SCCGWA;
    BPCUCMOV BPCUCMOV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCUCMOV BPCUCMOV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUCMOV = BPCUCMOV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUCMOV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_GD_INFO_PROC();
            if (pgmRtn) return;
            B040_GET_PROD_INFO();
            if (pgmRtn) return;
            B050_VCH_PROC_FOR_CN();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_GD_INFO_PROC();
            if (pgmRtn) return;
            B040_GET_PROD_INFO();
            if (pgmRtn) return;
            B050_VCH_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCUCMOV.MOVE_DATE == 0 
            || BPCUCMOV.CONF_SEQ == 0 
            || BPCUCMOV.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GD_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCMOV);
        IBS.init(SCCGWA, BPCRMOVF);
        BPRCMOV.KEY.MOV_DT = BPCUCMOV.MOVE_DATE;
        BPRCMOV.KEY.CONF_NO = BPCUCMOV.CONF_SEQ;
        BPRCMOV.KEY.CASH_TYP = BPCUCMOV.CASH_TYP;
        BPRCMOV.KEY.CCY = BPCUCMOV.CCY;
        CEP.TRC(SCCGWA, "BPZTMOVF123");
        CEP.TRC(SCCGWA, BPCUCMOV.MOVE_DATE);
        CEP.TRC(SCCGWA, BPCUCMOV.CONF_SEQ);
        CEP.TRC(SCCGWA, BPCUCMOV.CCY);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.MOV_DT);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.CONF_NO);
        CEP.TRC(SCCGWA, BPRCMOV.KEY.CCY);
        BPCRMOVF.LEN = 228;
        BPCRMOVF.INFO.FUNC = 'R';
        BPCRMOVF.POINTER = BPRCMOV;
        S000_CALL_BPZTMOVF();
        if (pgmRtn) return;
        BPCUCMOV.BV_DATE = BPRCMOV.BV_DT;
        BPCUCMOV.BV_CODE = BPRCMOV.BV_CODE;
        CEP.TRC(SCCGWA, BPRCMOV.BV_CODE);
        CEP.TRC(SCCGWA, BPCUCMOV.BV_CODE);
        BPCUCMOV.BV_NO = BPRCMOV.BV_NO;
        CEP.TRC(SCCGWA, BPRCMOV.BV_NO);
        if (BPRCMOV.MOV_STS != '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR, BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPRCMOV.IN_TLR = BPCUCMOV.IN_TLR;
        BPRCMOV.IN_BR = BPCUCMOV.IN_BR;
        BPRCMOV.IN_AC = BPCUCMOV.IN_AC;
        BPRCMOV.MOV_STS = '2';
        BPCRMOVF.LEN = 228;
        BPCRMOVF.INFO.FUNC = 'U';
        BPCRMOVF.POINTER = BPRCMOV;
        S000_CALL_BPZTMOVF();
        if (pgmRtn) return;
    }
    public void B040_GET_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPPRDQ);
        BPCPPRDQ.DATA_INFO.CCY = BPCUCMOV.CCY;
        BPCPPRDQ.DATA_INFO.STAT = '3';
        BPCPPRDQ.DATA_INFO.CS_KIND = BPCUCMOV.CS_KIND;
    }
    public void B050_VCH_PROC_FOR_CN() throws IOException,SQLException,Exception {
        B052_SET_EWA_EVENTS_FOR_CN();
        if (pgmRtn) return;
    }
    public void B050_VCH_PROC() throws IOException,SQLException,Exception {
        B051_SET_EWA_BASIC_INF();
        if (pgmRtn) return;
        B052_SET_EWA_EVENTS();
        if (pgmRtn) return;
    }
    public void B051_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
        if (pgmRtn) return;
    }
    public void B052_SET_EWA_EVENTS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        if (BPCUCMOV.MOV_TYPE == '6') {
            BPCPOEWA.DATA.EVENT_CODE = "CSQFZCR";
        } else {
            if (BPCUCMOV.MOV_TYPE == '4' 
                || BPCUCMOV.MOV_TYPE == '5') {
                BPCPOEWA.DATA.EVENT_CODE = "CSATMOWC";
            } else {
                BPCPOEWA.DATA.EVENT_CODE = "CSWAYCR";
            }
        }
        if (BPCUCMOV.MOV_TYPE == '1') {
            BPCPOEWA.DATA.BR_OLD = BPCUCMOV.IN_BR;
        } else {
            BPCPOEWA.DATA.BR_OLD = BPCUCMOV.OUT_BR;
        }
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUCMOV.CCY;
        BPCPOEWA.DATA.BR_NEW = BPCUCMOV.OUT_BR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUCMOV.TOTAL_AMT;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B052_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        BPCPOEWA.DATA.EVENT_CODE = "WAYCSSUB";
        if (BPCUCMOV.MOV_TYPE == '1') {
            BPCPOEWA.DATA.BR_OLD = BPCUCMOV.IN_BR;
        } else {
            BPCPOEWA.DATA.BR_OLD = BPCUCMOV.OUT_BR;
        }
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUCMOV.CCY;
        CEP.TRC(SCCGWA, BPCUCMOV.OUT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        BPCPOEWA.DATA.BR_NEW = BPCUCMOV.OUT_BR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUCMOV.TOTAL_AMT;
        CEP.TRC(SCCGWA, BPCUCMOV.CCY);
        if (BPCPOEWA.DATA.AC_NO == null) BPCPOEWA.DATA.AC_NO = "";
        JIBS_tmp_int = BPCPOEWA.DATA.AC_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCPOEWA.DATA.AC_NO += " ";
        if (BPCUCMOV.CCY == null) BPCUCMOV.CCY = "";
        JIBS_tmp_int = BPCUCMOV.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCUCMOV.CCY += " ";
        BPCPOEWA.DATA.AC_NO = BPCPOEWA.DATA.AC_NO.substring(0, 9 - 1) + BPCUCMOV.CCY + BPCPOEWA.DATA.AC_NO.substring(9 + 3 - 1);
        if (BPCPOEWA.DATA.AC_NO == null) BPCPOEWA.DATA.AC_NO = "";
        JIBS_tmp_int = BPCPOEWA.DATA.AC_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCPOEWA.DATA.AC_NO += " ";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO.substring(9 - 1, 9 + 3 - 1));
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTMOVF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CMOV, BPCRMOVF);
        if (BPCRMOVF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMOVF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTPARF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_MPAR, BPCRPARF);
        if (BPCRPARF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRPARF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CHIS, BPCRCHIS);
        if (BPCRCHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTHPAR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_HPAR, BPCRHPAR);
        if (BPCRHPAR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHPAR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPPRDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CASH_PROD_INQ, BPCPPRDQ);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUCMOV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUCMOV = ");
            CEP.TRC(SCCGWA, BPCUCMOV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
