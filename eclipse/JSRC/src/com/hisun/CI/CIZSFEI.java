package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSFEI {
    boolean pgmRtn = false;
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    String WS_SVS_ADC_NO = " ";
    String WS_CI_NO = " ";
    char WS_SVS_ORG_CLS = ' ';
    char WS_SVS_ADC_STS = ' ';
    char WS_LMT_TYP = ' ';
    char WS_LMT_KND = ' ';
    String WS_LMT_CCY = " ";
    double WS_BAL_LMT = 0;
    double WS_CR_TOT_LMT_AMT = 0;
    double WS_CR_TOT_CAM = 0;
    double WS_DR_TOT_LMT_AMT = 0;
    double WS_DR_TOT_CAM = 0;
    char WS_FLRG_TXN_TYP = ' ';
    short WS_SEQ = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRFLMT CIRFLMT = new CIRFLMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRFLMT CIRFLMTO = new CIRFLMT();
    CIRFLMT CIRFLMTN = new CIRFLMT();
    CIRFLRG CIRFLRG = new CIRFLRG();
    CICOSFEI CICOSFEI = new CICOSFEI();
    CIRFLRL CIRFLRL = new CIRFLRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSFEI CICSFEI;
    public void MP(SCCGWA SCCGWA, CICSFEI CICSFEI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSFEI = CICSFEI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSFEI return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSFEI.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSFEI.FUNC == 'A') {
            B020_ADD_FLMT_INF();
            if (pgmRtn) return;
        } else if (CICSFEI.FUNC == 'M') {
            B030_MOD_FLMT_INF();
            if (pgmRtn) return;
        } else if (CICSFEI.FUNC == 'D') {
            B040_DEL_FLMT_INF();
            if (pgmRtn) return;
        } else if (CICSFEI.FUNC == 'I') {
            B050_INQ_FLMT_INF();
            if (pgmRtn) return;
        } else if (CICSFEI.FUNC == 'B') {
            B060_BRW_FLMT_INF();
            if (pgmRtn) return;
        } else if (CICSFEI.FUNC == 'R') {
            B070_REP_FLMT_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC INPUT ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSFEI.DATA.CI_NO);
        if (CICSFEI.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICSFEI.DATA.CI_NO;
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                || CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "BAS INF NOT FND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSFEI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (CIRBAS.CI_TYP == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型不符");
            }
        }
    }
    public void B020_ADD_FLMT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLMT);
        IBS.init(SCCGWA, CIRFLMTO);
        IBS.init(SCCGWA, CIRFLMTN);
        if (CICSFEI.DATA.CI_NO.trim().length() == 0 
            || CICSFEI.DATA.SVS_ADC_NO.trim().length() == 0 
            || CICSFEI.DATA.LMT_TYP == ' ' 
            || CICSFEI.DATA.LMT_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO;
        T000_READ_CITFLMT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FLMT INF EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_EXIST, CICSFEI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRFLMT);
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO;
        CIRFLMT.CI_NO = CICSFEI.DATA.CI_NO;
        CIRFLMT.SVS_ORG_CLS = '0';
        CIRFLMT.SVS_ADC_STS = '0';
        CIRFLMT.LMT_TYP = CICSFEI.DATA.LMT_TYP;
        CIRFLMT.LMT_KND = '1';
        CIRFLMT.LTM_CCY = CICSFEI.DATA.LMT_CCY;
        CEP.TRC(SCCGWA, CICSFEI.DATA.LMT_TYP);
        if (CICSFEI.DATA.LMT_TYP == '0') {
        } else if (CICSFEI.DATA.LMT_TYP == '1') {
            if (CICSFEI.DATA.BAL_LMT == 0) {
                CEP.TRC(SCCGWA, "BAL LMT MUST INPUT");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TYP_INPUT_ERR, CICSFEI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CIRFLMT.BAL_LMT = CICSFEI.DATA.BAL_LMT;
        } else if (CICSFEI.DATA.LMT_TYP == '2') {
            if (CICSFEI.DATA.CR_TOT_LMT_AMT == 0) {
                CEP.TRC(SCCGWA, "CR LMT MUST INPUT");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TYP_INPUT_ERR, CICSFEI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CIRFLMT.CR_TOT_LMT_AMT = CICSFEI.DATA.CR_TOT_LMT_AMT;
        } else if (CICSFEI.DATA.LMT_TYP == '3') {
            if (CICSFEI.DATA.DR_TOT_LMT_AMT == 0) {
                CEP.TRC(SCCGWA, "DR LMT MUST INPUT");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TYP_INPUT_ERR, CICSFEI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CIRFLMT.DR_TOT_LMT_AMT = CICSFEI.DATA.DR_TOT_LMT_AMT;
        } else {
            CEP.TRC(SCCGWA, "LMT TYP INPUT ERR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TYP_INPUT_ERR, CICSFEI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CIRFLMT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLMT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLMT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRFLMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITFLMT();
        if (pgmRtn) return;
        WS_FLRG_TXN_TYP = '1';
        R000_WRITE_CITFLRG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFLMT, CIRFLMTO);
        IBS.CLONE(SCCGWA, CIRFLMT, CIRFLMTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B030_MOD_FLMT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLMT);
        IBS.init(SCCGWA, CIRFLMTO);
        IBS.init(SCCGWA, CIRFLMTN);
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO;
        T000_READ_CITFLMT_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICSFEI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRFLMT, CIRFLMTO);
        CIRFLMT.CI_NO = CICSFEI.DATA.CI_NO;
        CIRFLMT.SVS_ORG_CLS = CICSFEI.DATA.SVS_ORG_CLS;
        CIRFLMT.LMT_TYP = CICSFEI.DATA.LMT_TYP;
        CIRFLMT.LMT_KND = CICSFEI.DATA.LMT_KND;
        CIRFLMT.LTM_CCY = CICSFEI.DATA.LMT_CCY;
        if (CICSFEI.DATA.LMT_TYP == '0') {
        } else if (CICSFEI.DATA.LMT_TYP == '1') {
            if (CICSFEI.DATA.BAL_LMT == 0) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TYP_INPUT_ERR, CICSFEI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CIRFLMT.BAL_LMT = CICSFEI.DATA.BAL_LMT;
            CIRFLMT.CR_TOT_LMT_AMT = 0;
            CIRFLMT.DR_TOT_LMT_AMT = 0;
        } else if (CICSFEI.DATA.LMT_TYP == '2') {
            if (CICSFEI.DATA.CR_TOT_LMT_AMT == 0) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TYP_INPUT_ERR, CICSFEI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CIRFLMT.CR_TOT_LMT_AMT = CICSFEI.DATA.CR_TOT_LMT_AMT;
            CIRFLMT.BAL_LMT = 0;
            CIRFLMT.DR_TOT_LMT_AMT = 0;
        } else if (CICSFEI.DATA.LMT_TYP == '3') {
            if (CICSFEI.DATA.DR_TOT_LMT_AMT == 0) {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TYP_INPUT_ERR, CICSFEI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CIRFLMT.DR_TOT_LMT_AMT = CICSFEI.DATA.DR_TOT_LMT_AMT;
            CIRFLMT.BAL_LMT = 0;
            CIRFLMT.CR_TOT_LMT_AMT = 0;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_LMT_TYP_INPUT_ERR, CICSFEI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CIRFLMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITFLMT();
        if (pgmRtn) return;
        WS_FLRG_TXN_TYP = '2';
        R000_WRITE_CITFLRG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFLMT, CIRFLMTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_DEL_FLMT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLMT);
        IBS.init(SCCGWA, CIRFLMTO);
        IBS.init(SCCGWA, CIRFLMTN);
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO;
        T000_READ_CITFLMT_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICSFEI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRFLMT, CIRFLMTO);
        CIRFLMT.SVS_ADC_STS = '2';
        CIRFLMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITFLMT();
        if (pgmRtn) return;
        WS_FLRG_TXN_TYP = '3';
        R000_WRITE_CITFLRG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFLMT, CIRFLMTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B050_INQ_FLMT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLMT);
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO;
        T000_READ_CITFLMT_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICSFEI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_DATA_TRANS_TO_FMTMPAG();
        if (pgmRtn) return;
        B020_01_INQ_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B060_BRW_FLMT_INF() throws IOException,SQLException,Exception {
        if (CICSFEI.DATA.SVS_ADC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRFLMT);
            CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO;
            T000_READ_CITFLMT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICSFEI.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, CIRFLMT);
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO;
        CIRFLMT.CI_NO = CICSFEI.DATA.CI_NO;
        T000_STARTBR_CITFLMT_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITFLMT();
        if (pgmRtn) return;
        B020_02_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B020_03_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITFLMT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITFLMT();
        if (pgmRtn) return;
    }
    public void B070_REP_FLMT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLMT);
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO_N;
        T000_READ_CITFLMT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_EXIST, CICSFEI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRFLMT);
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO_O;
        T000_READ_CITFLMT_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICSFEI.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRFLMT, CIRFLMTO);
        CIRFLMT.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO_N;
        CIRFLMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITFLMT();
        if (pgmRtn) return;
        WS_FLRG_TXN_TYP = '3';
        R000_WRITE_CITFLRG();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFLMT, CIRFLMTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRFLRL);
        CIRFLRL.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO_O;
        T000_STARTBR_CITFLRL_UPD();
        if (pgmRtn) return;
        T000_READNEXT_CITFLRL();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CIRFLRL.KEY.SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO_N;
            CIRFLRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRFLRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRFLRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITFLRL();
            if (pgmRtn) return;
            T000_READNEXT_CITFLRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITFLRL();
        if (pgmRtn) return;
    }
    public void B020_01_INQ_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO09";
        SCCFMT.DATA_PTR = CICOSFEI;
        SCCFMT.DATA_LEN = 170;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_02_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_STS;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_03_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        R000_DATA_TRANS_TO_FMTMPAG();
        if (pgmRtn) return;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOSFEI);
        SCCMPAG.DATA_LEN = 170;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_WRITE_CITFLRG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLRG);
        CIRFLRG.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLRG.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, CIRFLRG.KEY.JRN_NO);
        T000_READ_CITFLRG_FIRST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SEQ = 1;
        } else {
            WS_SEQ = (short) (CIRFLRG.KEY.SEQ + 1);
        }
        IBS.init(SCCGWA, CIRFLRG);
        CIRFLRG.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLRG.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CIRFLRG.KEY.SEQ = WS_SEQ;
        CIRFLRG.TXN_TYP = WS_FLRG_TXN_TYP;
        CEP.TRC(SCCGWA, CIRFLRG.TXN_TYP);
        CIRFLRG.AGR_NO = CICSFEI.DATA.AGR_NO;
        CIRFLRG.CI_NO = CIRFLMT.CI_NO;
        CIRFLRG.SVS_ADC_NO = CIRFLMT.KEY.SVS_ADC_NO;
        CIRFLRG.SVS_ORG_CLS = CIRFLMT.SVS_ORG_CLS;
        CIRFLRG.RPL_SVS_ADC_NO = CICSFEI.DATA.SVS_ADC_NO_N;
        CIRFLRG.LMT_TYP = CIRFLMT.LMT_TYP;
        CIRFLRG.LMT_KND = CIRFLMT.LMT_KND;
        CIRFLRG.LTM_CCY = CIRFLMT.LTM_CCY;
        CIRFLRG.BAL_LMT = CIRFLMT.BAL_LMT;
        CIRFLRG.CR_TOT_LMT_AMT = CIRFLMT.CR_TOT_LMT_AMT;
        CIRFLRG.DR_TOT_LMT_AMT = CIRFLMT.DR_TOT_LMT_AMT;
        CIRFLRG.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLRG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLRG.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLRG.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLRG.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRFLRG.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITFLRG();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_FMTMPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOSFEI);
        CICOSFEI.DATA.SVS_ADC_NO = CIRFLMT.KEY.SVS_ADC_NO;
        CICOSFEI.DATA.CI_NO = CIRFLMT.CI_NO;
        CICOSFEI.DATA.SVS_ORG_CLS = CIRFLMT.SVS_ORG_CLS;
        CICOSFEI.DATA.SVS_ADC_STS = CIRFLMT.SVS_ADC_STS;
        CICOSFEI.DATA.LMT_TYP = CIRFLMT.LMT_TYP;
        CICOSFEI.DATA.LMT_KND = CIRFLMT.LMT_KND;
        CICOSFEI.DATA.LMT_CCY = CIRFLMT.LTM_CCY;
        CICOSFEI.DATA.BAL_LMT = CIRFLMT.BAL_LMT;
        CICOSFEI.DATA.CR_TOT_LMT_AMT = CIRFLMT.CR_TOT_LMT_AMT;
        CICOSFEI.DATA.CR_TOT_CAM = CIRFLMT.CR_TOT_CAM;
        CICOSFEI.DATA.DR_TOT_LMT_AMT = CIRFLMT.DR_TOT_LMT_AMT;
        CICOSFEI.DATA.DR_TOT_CAM = CIRFLMT.DR_TOT_CAM;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CIRFLMT.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRFLMT";
        BPCPNHIS.INFO.FMT_ID_LEN = 239;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRFLMTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRFLMTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITFLMT() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        IBS.READ(SCCGWA, CIRFLMT, CITFLMT_RD);
    }
    public void T000_READ_CITFLMT_UPD() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        CITFLMT_RD.upd = true;
        IBS.READ(SCCGWA, CIRFLMT, CITFLMT_RD);
    }
    public void T000_STARTBR_CITFLMT_BY_CI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRFLMT.KEY.SVS_ADC_NO);
        CEP.TRC(SCCGWA, CIRFLMT.CI_NO);
        CITFLMT_BR.rp = new DBParm();
        CITFLMT_BR.rp.TableName = "CITFLMT";
        CITFLMT_BR.rp.where = "( SVS_ADC_NO = :CIRFLMT.KEY.SVS_ADC_NO "
            + "OR :CIRFLMT.KEY.SVS_ADC_NO = ' ' ) "
            + "AND ( CI_NO = :CIRFLMT.CI_NO "
            + "OR :CIRFLMT.CI_NO = ' ' )";
        IBS.STARTBR(SCCGWA, CIRFLMT, this, CITFLMT_BR);
    }
    public void T000_READNEXT_CITFLMT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRFLMT, this, CITFLMT_BR);
    }
    public void T000_ENDBR_CITFLMT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITFLMT_BR);
    }
    public void T000_WRITE_CITFLMT() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        IBS.WRITE(SCCGWA, CIRFLMT, CITFLMT_RD);
    }
    public void T000_REWRITE_CITFLMT() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        IBS.REWRITE(SCCGWA, CIRFLMT, CITFLMT_RD);
    }
    public void T000_READ_CITFLRG_FIRST() throws IOException,SQLException,Exception {
        CITFLRG_RD = new DBParm();
        CITFLRG_RD.TableName = "CITFLRG";
        CITFLRG_RD.eqWhere = "AC_DATE,JRN_NO";
        CITFLRG_RD.fst = true;
        CITFLRG_RD.order = "SEQ DESC";
        IBS.READ(SCCGWA, CIRFLRG, CITFLRG_RD);
    }
    public void T000_WRITE_CITFLRG() throws IOException,SQLException,Exception {
        CITFLRG_RD = new DBParm();
        CITFLRG_RD.TableName = "CITFLRG";
        IBS.WRITE(SCCGWA, CIRFLRG, CITFLRG_RD);
    }
    public void T000_STARTBR_CITFLRL_UPD() throws IOException,SQLException,Exception {
