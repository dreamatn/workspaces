package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZURMOV {
    DBParm BPTALIB_RD;
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_MOV_SEQ_TYPE = "CMOVE";
    String K_MOV_SEQ_NAME = "CASHMOVE";
    String CPN_R_ADW_CMOV = "BP-R-ADW-CMOV       ";
    String CPN_R_ADW_MPAR = "BP-R-ADW-MPAR       ";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ        ";
    String CPN_R_ADW_CHIS = "BP-R-ADW-CHIS       ";
    String CPN_R_ADW_HPAR = "BP-R-ADW-HPAR       ";
    String CPN_P_CASH_PROD_INQ = "BP-P-CASH-PROD-INQ";
    String BP_P_CHK_WORK_DAY = "BP-P-CHK-WORK-DAY";
    int WS_INDEX = 0;
    BPZURMOV_WS_RGNC_KEY WS_RGNC_KEY = new BPZURMOV_WS_RGNC_KEY();
    BPZURMOV_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZURMOV_WS_EWA_AC_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCMOV BPRCMOV = new BPRCMOV();
    BPRMPAR BPRMPAR = new BPRMPAR();
    BPRCHIS BPRCHIS = new BPRCHIS();
    BPRHPAR BPRHPAR = new BPRHPAR();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCRPARF BPCRPARF = new BPCRPARF();
    BPCRMOVF BPCRMOVF = new BPCRMOVF();
    BPCRHPAR BPCRHPAR = new BPCRHPAR();
    BPCRCHIS BPCRCHIS = new BPCRCHIS();
    BPCPPRDQ BPCPPRDQ = new BPCPPRDQ();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPRALIB BPRALIB = new BPRALIB();
    SCCGWA SCCGWA;
    BPCURMOV BPCURMOV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCURMOV BPCURMOV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCURMOV = BPCURMOV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZURMOV return!");
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
            B030_WAY_DETAILS_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B040_GET_PROD_INFO();
                if (pgmRtn) return;
                B050_VCH_PROC_FOR_CN();
                if (pgmRtn) return;
            }
        } else {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B020_GD_INFO_PROC();
            if (pgmRtn) return;
            B030_WAY_DETAILS_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B040_GET_PROD_INFO();
                if (pgmRtn) return;
                B050_VCH_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCURMOV.MOVE_DATE == 0 
            || BPCURMOV.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GD_INFO_PROC() throws IOException,SQLException,Exception {
        if (BPCURMOV.CONF_SEQ == 0) {
            B020_01_GEN_SEQ();
            if (pgmRtn) return;
            BPCURMOV.CONF_SEQ = (int) BPCSGSEQ.SEQ;
        }
        CEP.TRC(SCCGWA, "BPZURMOV-SGSEQ");
        IBS.init(SCCGWA, BPRCMOV);
        IBS.init(SCCGWA, BPCRMOVF);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            CEP.TRC(SCCGWA, BPCURMOV.MOVE_DATE);
            CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
            CEP.TRC(SCCGWA, BPCURMOV.CCY);
            CEP.TRC(SCCGWA, BPCURMOV.MOV_TYPE);
            BPRCMOV.KEY.MOV_DT = BPCURMOV.MOVE_DATE;
            BPRCMOV.KEY.CONF_NO = BPCURMOV.CONF_SEQ;
            BPRCMOV.KEY.CASH_TYP = BPCURMOV.CASH_TYP;
            BPRCMOV.KEY.CCY = BPCURMOV.CCY;
            BPRCMOV.MOV_TYP = BPCURMOV.MOV_TYPE;
            BPRCMOV.MOV_STS = '1';
            BPRCMOV.CS_KIND = BPCURMOV.CS_KIND;
            BPRCMOV.AMT = BPCURMOV.TOTAL_AMT;
            BPRCMOV.OUT_BR = BPCURMOV.OUT_BR;
            BPRCMOV.OUT_TLR = BPCURMOV.OUT_TLR;
            BPRCMOV.OUT_AC = BPCURMOV.OUT_AC;
            BPRCMOV.IN_BR = BPCURMOV.IN_BR;
            BPRCMOV.IN_TLR = BPCURMOV.IN_TLR;
            BPRCMOV.IN_AC = BPCURMOV.IN_AC;
            BPRCMOV.BV_DT = BPCURMOV.BV_DATE;
            BPRCMOV.BV_CODE = BPCURMOV.BV_CODE;
            BPRCMOV.BV_NO = BPCURMOV.BV_NO;
            BPRCMOV.TO_BANK = BPCURMOV.TO_BANK;
            BPRCMOV.ONWAY_DT = BPCURMOV.ONWAY_DT;
            CEP.TRC(SCCGWA, BPRCMOV.ONWAY_DT);
            BPRCMOV.ALLOT_TYPE = BPCURMOV.ALLOT_TP;
            BPCRMOVF.LEN = 228;
            BPCRMOVF.INFO.FUNC = 'A';
            BPCRMOVF.POINTER = BPRCMOV;
            S000_CALL_BPZTMOVF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, BPCURMOV.MOVE_DATE);
            CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
            CEP.TRC(SCCGWA, BPCURMOV.CCY);
            BPRCMOV.KEY.MOV_DT = BPCURMOV.MOVE_DATE;
            BPRCMOV.KEY.CONF_NO = BPCURMOV.CONF_SEQ;
            BPRCMOV.KEY.CASH_TYP = BPCURMOV.CASH_TYP;
            BPRCMOV.KEY.CCY = BPCURMOV.CCY;
            BPCRMOVF.LEN = 228;
            BPCRMOVF.INFO.FUNC = 'R';
            BPCRMOVF.POINTER = BPRCMOV;
            S000_CALL_BPZTMOVF();
            if (pgmRtn) return;
            if (BPRCMOV.MOV_STS != '1') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR, BPCURMOV.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            BPRCMOV.MOV_STS = '3';
            BPCRMOVF.LEN = 228;
            BPCRMOVF.INFO.FUNC = 'U';
            BPCRMOVF.POINTER = BPRCMOV;
            S000_CALL_BPZTMOVF();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPRALIB);
            BPRALIB.CONF_NO = BPCURMOV.CONF_SEQ;
            BPRALIB.APP_STS = '5';
            S000_READ_BPTALIB();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (BPRALIB.APP_TYPE == '0') {
                    BPRALIB.APP_STS = '4';
                } else {
                    if (BPRALIB.APP_TYPE == '1') {
                        BPRALIB.APP_STS = '0';
                    }
                }
                BPRALIB.CONF_NO = 0;
                T000_REWRITE_BPTALIB();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_READ_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        BPTALIB_RD.where = "CONF_NO = :BPRALIB.CONF_NO "
            + "AND APP_STS = :BPRALIB.APP_STS";
        BPTALIB_RD.upd = true;
        BPTALIB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRALIB, this, BPTALIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTALIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTALIB() throws IOException,SQLException,Exception {
        BPTALIB_RD = new DBParm();
        BPTALIB_RD.TableName = "BPTALIB";
        IBS.REWRITE(SCCGWA, BPRALIB, BPTALIB_RD);
    }
    public void B020_01_GEN_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        BPCSGSEQ.TYPE = K_MOV_SEQ_TYPE;
        BPCSGSEQ.CODE = K_MOV_SEQ_NAME;
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
    }
    public void B030_WAY_DETAILS_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            for (WS_INDEX = 1; WS_INDEX <= 20 
                && BPCURMOV.CCY_INFO.CCY_DETL[WS_INDEX-1].CCY_VAL != 0; WS_INDEX += 1) {
                IBS.init(SCCGWA, BPRMPAR);
                IBS.init(SCCGWA, BPCRPARF);
                BPRMPAR.KEY.MOV_DT = BPCURMOV.MOVE_DATE;
                BPRMPAR.KEY.CONF_NO = BPCURMOV.CONF_SEQ;
                BPRMPAR.KEY.CASH_TYP = BPCURMOV.CASH_TYP;
                BPRMPAR.KEY.CCY = BPCURMOV.CCY;
                BPRMPAR.KEY.VAL = BPCURMOV.CCY_INFO.CCY_DETL[WS_INDEX-1].CCY_VAL;
                BPRMPAR.KEY.MFLG = BPCURMOV.CCY_INFO.CCY_DETL[WS_INDEX-1].CCY_MFLG;
                BPRMPAR.NUM = BPCURMOV.CCY_INFO.CCY_DETL[WS_INDEX-1].CCY_NUM;
                BPCRPARF.INFO.FUNC = 'A';
                BPCRPARF.LEN = 74;
                BPCRPARF.POINTER = BPRMPAR;
                S000_CALL_BPZTPARF();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_GET_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPPRDQ);
        BPCPPRDQ.DATA_INFO.CCY = BPCURMOV.CCY;
        BPCPPRDQ.DATA_INFO.STAT = '3';
        BPCPPRDQ.DATA_INFO.CS_KIND = BPCURMOV.CS_KIND;
        CEP.TRC(SCCGWA, BPCPPRDQ.RC);
        CEP.TRC(SCCGWA, BPCPPRDQ.DATA_INFO.ACCT_CD);
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
        CEP.TRC(SCCGWA, BPCURMOV.MOV_TYPE);
        if (BPCURMOV.MOV_TYPE == '6') {
            BPCPOEWA.DATA.EVENT_CODE = "CSQFZDR";
        } else {
            if (BPCURMOV.MOV_TYPE == '4' 
                || BPCURMOV.MOV_TYPE == '5') {
                BPCPOEWA.DATA.EVENT_CODE = "CSATMOWD";
            } else {
                BPCPOEWA.DATA.EVENT_CODE = "CSWAYDR";
            }
        }
        CEP.TRC(SCCGWA, "NCB0206001");
        CEP.TRC(SCCGWA, BPCURMOV.MOV_TYPE);
        if (BPCURMOV.MOV_TYPE == '1') {
            BPCPOEWA.DATA.BR_OLD = BPCURMOV.IN_BR;
        } else {
            BPCPOEWA.DATA.BR_OLD = BPCURMOV.OUT_BR;
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.BR_OLD);
        BPCPOEWA.DATA.BR_NEW = BPCURMOV.IN_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCURMOV.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCURMOV.TOTAL_AMT;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B052_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CAS";
        BPCPOEWA.DATA.EVENT_CODE = "WAYCSADD";
        if (BPCURMOV.MOV_TYPE == '1') {
            BPCPOEWA.DATA.BR_OLD = BPCURMOV.IN_BR;
        } else {
            BPCPOEWA.DATA.BR_OLD = BPCURMOV.OUT_BR;
        }
        BPCPOEWA.DATA.BR_NEW = BPCURMOV.IN_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCURMOV.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCURMOV.TOTAL_AMT;
        if (BPCPOEWA.DATA.AC_NO == null) BPCPOEWA.DATA.AC_NO = "";
        JIBS_tmp_int = BPCPOEWA.DATA.AC_NO.length();
        for (int i=0;i<25-JIBS_tmp_int;i++) BPCPOEWA.DATA.AC_NO += " ";
        if (BPCURMOV.CCY == null) BPCURMOV.CCY = "";
        JIBS_tmp_int = BPCURMOV.CCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCURMOV.CCY += " ";
        BPCPOEWA.DATA.AC_NO = BPCPOEWA.DATA.AC_NO.substring(0, 9 - 1) + BPCURMOV.CCY + BPCPOEWA.DATA.AC_NO.substring(9 + 3 - 1);
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZTMOVF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CMOV, BPCRMOVF);
        if (BPCRMOVF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMOVF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTPARF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_MPAR, BPCRPARF);
        if (BPCRPARF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRPARF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_CHIS, BPCRCHIS);
        if (BPCRCHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTHPAR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_HPAR, BPCRHPAR);
        if (BPCRHPAR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRHPAR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPPRDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CASH_PROD_INQ, BPCPPRDQ);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_P_CHK_WORK_DAY, BPCOCKWD);
        if (BPCPPRDQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPPRDQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCURMOV.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCURMOV = ");
            CEP.TRC(SCCGWA, BPCURMOV);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
