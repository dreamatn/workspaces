package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQCMZ {
    DBParm BPTFESIR_RD;
    brParm BPTCMZR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_F_MAINTAIN_CMZR = "BP-F-R-MAINTAIN-CMZR";
    String WS_ERR_MSG = " ";
    int WS_ATM_NUM = 0;
    BPZPQCMZ_WS_OCMZ_KEY WS_OCMZ_KEY = new BPZPQCMZ_WS_OCMZ_KEY();
    BPZPQCMZ_WS_OUTPUT WS_OUTPUT = new BPZPQCMZ_WS_OUTPUT();
    char WS_BRCH_CHECK_FLAG = ' ';
    char WS_ENTI_FLG = ' ';
    char WS_QUIT_FLG = ' ';
    char WS_DATE = ' ';
    char WS_CMZ_FLG1 = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCRCMZR BPCRCMZR = new BPCRCMZR();
    BPRCMZR BPRCMZR = new BPRCMZR();
    DCCUATMS DCCUATMS = new DCCUATMS();
    BPRFESIR BPRFESIR = new BPRFESIR();
    SCCGWA SCCGWA;
    BPCPQCMZ BPCPQCMZ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQCMZ BPCPQCMZ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQCMZ = BPCPQCMZ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQCMZ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPQCMZ.RC);
        IBS.init(SCCGWA, BPRCMZR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_GET_CMZ_INFO();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQCMZ.CI_NO);
        if (BPCPQCMZ.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQCMZ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPQCMZ.FEE_CODE);
        if (BPCPQCMZ.FEE_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQCMZ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPQCMZ.INQ_DT);
        if (BPCPQCMZ.INQ_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQCMZ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_CMZ_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQCMZ.CI_NO);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_AC);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CARD);
        BPRCMZR.KEY.CI_NO = BPCPQCMZ.CI_NO;
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        B070_START_BROWSE();
        if (pgmRtn) return;
        B080_READ_NEXT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_AC);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.EFF_DATE);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.FEE_CODE);
        CEP.TRC(SCCGWA, BPRCMZR.EXP_DATE);
        CEP.TRC(SCCGWA, BPRCMZR.ENTI_FLG);
        CEP.TRC(SCCGWA, BPRCMZR.CMZ_FLG1);
        CEP.TRC(SCCGWA, BPRCMZR.CMZ_FLG2);
        CEP.TRC(SCCGWA, BPRCMZR.KEY.FEE_CODE);
        CEP.TRC(SCCGWA, BPCPQCMZ.FEE_CODE);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_AC);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CARD);
        while (BPCRCMZR.RETURN_INFO != 'N' 
            && WS_QUIT_FLG != 'Y') {
            if (((BPCPQCMZ.INQ_DT >= BPRCMZR.KEY.EFF_DATE) 
                && (BPCPQCMZ.INQ_DT <= BPRCMZR.EXP_DATE)) 
                && ((BPRCMZR.KEY.CMZ_FLG == '1') 
                || (BPCPQCMZ.CMZ_AC.trim().length() > 0 
                && BPRCMZR.KEY.CMZ_FLG == '2' 
                && BPRCMZR.KEY.CMZ_AC.equalsIgnoreCase(BPCPQCMZ.CMZ_AC)) 
                || (BPCPQCMZ.CMZ_CARD.trim().length() > 0 
                && BPRCMZR.KEY.CMZ_FLG == '3' 
                && BPRCMZR.KEY.CMZ_AC.equalsIgnoreCase(BPCPQCMZ.CMZ_CARD)))) {
                S000_SET_WS_VALUE();
                if (pgmRtn) return;
            }
            B080_READ_NEXT();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_DATE);
        CEP.TRC(SCCGWA, WS_QUIT_FLG);
        CEP.TRC(SCCGWA, BPCRCMZR.RETURN_INFO);
        B090_END_BROWSE();
        if (pgmRtn) return;
        if (WS_QUIT_FLG != 'Y') {
            S000_GET_FESIR_INFO();
            if (pgmRtn) return;
        }
        BPCPQCMZ.DATE_FLG = WS_DATE;
        CEP.TRC(SCCGWA, BPCPQCMZ.RC);
    }
    public void B070_START_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCMZR);
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'T';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B080_READ_NEXT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCMZR);
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'N';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void B090_END_BROWSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCMZR);
        BPCRCMZR.INFO.FUNC = 'B';
        BPCRCMZR.INFO.OPT = 'E';
        BPCRCMZR.INFO.REC_LEN = 279;
        BPCRCMZR.INFO.POINTER = BPRCMZR;
        S000_CALL_BPZRCMZR();
        if (pgmRtn) return;
    }
    public void S000_GET_FESIR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFESIR);
        BPRFESIR.KEY.SIR_AC = BPCPQCMZ.CMZ_AC;
        BPTFESIR_RD = new DBParm();
        BPTFESIR_RD.TableName = "BPTFESIR";
        BPTFESIR_RD.eqWhere = "SIR_AC";
        IBS.READ(SCCGWA, BPRFESIR, BPTFESIR_RD);
        CEP.TRC(SCCGWA, BPRFESIR.EFF_DATE);
        CEP.TRC(SCCGWA, BPRFESIR.EXP_DATE);
        if (BPRFESIR.KEY.BIZ_NUM.trim().length() > 0 
            && BPRFESIR.EFF_DATE <= SCCGWA.COMM_AREA.AC_DATE 
            && BPRFESIR.EXP_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, BPRCMZR);
            BPRCMZR.KEY.CMZ_FLG = '4';
            BPRCMZR.KEY.CMZ_BIZ = BPRFESIR.KEY.BIZ_NUM;
            BPTCMZR_BR.rp = new DBParm();
            BPTCMZR_BR.rp.TableName = "BPTCMZR";
            BPTCMZR_BR.rp.eqWhere = "CMZ_FLG,CMZ_BIZ";
            IBS.STARTBR(SCCGWA, BPRCMZR, BPTCMZR_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            CEP.TRC(SCCGWA, WS_QUIT_FLG);
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && WS_QUIT_FLG != 'Y') {
                CEP.TRC(SCCGWA, BPRCMZR.KEY.EFF_DATE);
                CEP.TRC(SCCGWA, BPRCMZR.EXP_DATE);
                S000_GET_ATM_NUM();
                if (pgmRtn) return;
                if ((BPCPQCMZ.INQ_DT >= BPRCMZR.KEY.EFF_DATE) 
                    && (BPCPQCMZ.INQ_DT <= BPRCMZR.EXP_DATE) 
                    && (WS_ATM_NUM <= BPRCMZR.TRC_NUM)) {
                    S000_SET_WS_VALUE();
                    if (pgmRtn) return;
                }
                if (WS_QUIT_FLG != 'Y') {
                    S000_READ_NEXT();
                    if (pgmRtn) return;
                }
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NO CMZR");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CMZRINFO_NOTFND, BPCPQCMZ.RC);
        }
    }
    public void S000_SET_WS_VALUE() throws IOException,SQLException,Exception {
        if (BPRCMZR.ENTI_FLG == '0') {
            WS_DATE = 'Y';
            WS_QUIT_FLG = 'Y';
        } else {
            if (BPRCMZR.KEY.FEE_CODE.equalsIgnoreCase(BPCPQCMZ.FEE_CODE)) {
                WS_DATE = 'Y';
                WS_QUIT_FLG = 'Y';
            }
        }
        CEP.TRC(SCCGWA, BPRCMZR.KEY.CMZ_FLG);
        CEP.TRC(SCCGWA, BPRCMZR.TRC_NUM);
        BPCPQCMZ.CMZ_FLG1 = BPRCMZR.CMZ_FLG1;
        BPCPQCMZ.CMZ_FLG2 = BPRCMZR.CMZ_FLG2;
        BPCPQCMZ.CMZ_CCY = BPRCMZR.CMZ_CCY;
        BPCPQCMZ.CMZ_AMT = BPRCMZR.CMZ_AMT;
        BPCPQCMZ.CMZ_PCN = BPRCMZR.CMZ_PCN;
        BPCPQCMZ.ENTI_FLG = BPRCMZR.ENTI_FLG;
        BPCPQCMZ.CCY_RULE = BPRCMZR.CMZ_CCY_RULE;
        CEP.TRC(SCCGWA, BPCPQCMZ.CCY_RULE);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG1);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_FLG2);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_CCY);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_AMT);
        CEP.TRC(SCCGWA, BPCPQCMZ.CMZ_PCN);
        CEP.TRC(SCCGWA, BPCPQCMZ.ENTI_FLG);
    }
    public void S000_GET_ATM_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUATMS);
        DCCUATMS.FUNC = 'Q';
        DCCUATMS.CARD_NO = BPCPQCMZ.CMZ_AC;
        CEP.TRC(SCCGWA, BPCPQCMZ.FEE_CODE);
        DCCUATMS.TXN_TYPE = "02";
        DCCUATMS.REGN_TYPE = "02";
        if (BPCPQCMZ.FEE_CODE.equalsIgnoreCase("302001102")) {
            DCCUATMS.TC_FLG = "01";
        }
        if (BPCPQCMZ.FEE_CODE.equalsIgnoreCase("302001104")) {
            DCCUATMS.TC_FLG = "02";
        }
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DCCUATMS.MONTH = JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1);
        if (DCCUATMS.TC_FLG.trim().length() > 0) {
            IBS.CALLCPN(SCCGWA, "DC-U-ATMS-INF", DCCUATMS);
        }
        CEP.TRC(SCCGWA, DCCUATMS.OUTPUT.O_CNT);
        WS_ATM_NUM = DCCUATMS.OUTPUT.O_CNT;
    }
    public void S000_CALL_BPZRCMZR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_MAINTAIN_CMZR, BPCRCMZR);
        if (BPCRCMZR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRCMZR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_READ_NEXT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRCMZR, this, BPTCMZR_BR);
    }
    public void S000_END_BR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTCMZR_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQCMZ.RC.RC_CODE != 0) {
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
