package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSDLAY {
    int JIBS_tmp_int;
    DBParm LNTEXTN_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTPAIP_RD;
    DBParm LNTRCVD_RD;
    DBParm LNTRATL_RD;
    DBParm LNTRATN_RD;
    DBParm LNTLOAN_RD;
    DBParm LNTRELA_RD;
    DBParm LNTFWDH_RD;
    DBParm LNTAGRE_RD;
    boolean pgmRtn = false;
    String K_AC_TYPE = "13";
    String CPN_CI_CIZCUST = "CI-INQ-CUST";
    String K_HIS_REMARKS = "DELAY TRADE";
    String K_PPMQ_PROD_XBD = "P010";
    String K_PPMQ_PROD_FUND = "P008";
    String K_PPMQ_PROD_SYR = "P023";
    LNZSDLAY_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNZSDLAY_WS_TEMP_VARIABLE();
    int WS_LAST_TX_SEQ = 0;
    int WS_PLAJ_ST_DT = 0;
    int WS_LAST_TX_DT = 0;
    int WS_LAST_F_VAL_DATE = 0;
    double WS_PEN_IRAT = 0;
    double WS_CPND_IRAT = 0;
    double WS_ABUS_IRAT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_FND_FLG = ' ';
    char WS_EXIST_FWDH_FLAG = ' ';
    char WS_FIRST_RCVD = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCOWLAD LNCOWLAD = new LNCOWLAD();
    LNCICIQ LNCICIQ = new LNCICIQ();
    LNCURTL LNCSRTL = new LNCURTL();
    LNCURTN LNCSRTN = new LNCURTN();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNCRATNM LNCRATNM = new LNCRATNM();
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCIGECR LNCIGECR = new LNCIGECR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQORG BPCPQORG = new BPCPQORG();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    CICCUST CICCUST = new CICCUST();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    LNCCMTPM LNCCMTPM = new LNCCMTPM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRCONT LNRCONT = new LNRCONT();
    LNCRCONT LNCRCONT = new LNCRCONT();
    CICSSTC CICSSTC = new CICSSTC();
    LNRICTL LNRICTL = new LNRICTL();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRFWDH LNRFWDH = new LNRFWDH();
    LNCRFWDH LNCRFWDH = new LNCRFWDH();
    LNRRATN LNRRATN = new LNRRATN();
    LNCRRATN LNCRRATN = new LNCRRATN();
    BPCCINTI BPCCINTI = new BPCCINTI();
    LNCRRATL LNCRRATL = new LNCRRATL();
    LNRPLPI LNRPLPI = new LNRPLPI();
    LNCRPLPI LNCRPLPI = new LNCRPLPI();
    LNCRRCVD LNCRRCVD = new LNCRRCVD();
    LNRRCVD LNRRCVD = new LNRRCVD();
    LNCURAT LNCURAT = new LNCURAT();
    LNCICAL LNCICAL = new LNCICAL();
    LNCRCAL LNCRCAL = new LNCRCAL();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCSPINI LNCSPINI = new LNCSPINI();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNRRELA LNRRELA = new LNRRELA();
    LNRPAIP LNRPAIP = new LNRPAIP();
    LNREXTN LNREXTN = new LNREXTN();
    LNCREXTN LNCREXTN = new LNCREXTN();
    LNCRATLM LNCRATLM = new LNCRATLM();
    LNRRATL LNRRATL = new LNRRATL();
    LNCUEXT LNCUEXT = new LNCUEXT();
    LNCBKRAT LNCBKRAT = new LNCBKRAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    LNCSDLAY LNCSDLAY;
    public void MP(SCCGWA SCCGWA, LNCSDLAY LNCSDLAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSDLAY = LNCSDLAY;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSDLAY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        LNCSDLAY.RC.RC_APP = "LN";
        LNCSDLAY.RC.RC_RTNCODE = 0;
    }
    CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.CONT_NO);
    CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.VAL_DT);
    CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.DMATU_DT);
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_INFO();
        if (pgmRtn) return;
        B020_CHECK_DATA();
        if (pgmRtn) return;
        B030_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_GET_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCONT);
        IBS.init(SCCGWA, LNRCONT);
        LNCRCONT.FUNC = 'I';
        LNRCONT.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNCRICTL.FUNC = 'I';
        LNRICTL.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.CONT_NO);
        CEP.TRC(SCCGWA, LNRICTL.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRICTL.KEY.SUB_CTA_NO);
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        if (LNCSDLAY.COMM_DATA.AMT == 0) {
            LNCSDLAY.COMM_DATA.AMT = LNCCLNQ.DATA.TOT_BAL;
        }
        if (LNCSDLAY.COMM_DATA.BAL == 0) {
            LNCSDLAY.COMM_DATA.BAL = LNCCLNQ.DATA.TOT_BAL;
        }
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B020_CHECK_DATA() throws IOException,SQLException,Exception {
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(47 - 1, 47 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
            || LNRICTL.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_STS_NOT_NORMAL, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "ICTL-CTL-STSW");
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW);
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNRICTL.CTL_STSW.substring(35 - 1, 35 + 1 - 1));
        if (LNCSDLAY.COMM_DATA.VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            T00_READ_FWD_EXTN();
            if (pgmRtn) return;
        }
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DRAW_TYPE, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCSDLAY.COMM_DATA.VAL_DT < LNRCONT.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DLAY_DT_LT_TR_DT, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.DMATU_DT);
        CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.MATU_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
        if ((LNCSDLAY.COMM_DATA.DMATU_DT == LNCSDLAY.COMM_DATA.MATU_DT 
            || (LNCSDLAY.COMM_DATA.DMATU_DT <= SCCGWA.COMM_AREA.AC_DATE 
            && (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP"))) 
            || LNCSDLAY.COMM_DATA.DMATU_DT <= LNCSDLAY.COMM_DATA.VAL_DT 
            || LNCSDLAY.COMM_DATA.DMATU_DT <= LNRCONT.MAT_DATE) 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DMATU_DT, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNRCONT.START_DATE);
        CEP.TRC(SCCGWA, LNRCONT.MAT_DATE);
    }
    public void B030_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        if ((!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP"))) {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B031_WRITE_EXTN();
                if (pgmRtn) return;
            }
        }
        if (LNCSDLAY.COMM_DATA.VAL_DT <= SCCGWA.COMM_AREA.AC_DATE 
            || SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            B032_DELAY_PROC();
            if (pgmRtn) return;
        }
        if (LNCSDLAY.COMM_DATA.VAL_DT > SCCGWA.COMM_AREA.AC_DATE 
            && SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            R000_DEL_LNTEXTN();
            if (pgmRtn) return;
        }
    }
    public void B032_DELAY_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B035_UEXT_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.PEN_FLG);
            if (LNCSDLAY.COMM_DATA.PEN_FLG == 'Y') {
                S000_CALL_LNZSPINI();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.PEN_FLG);
            if (LNCSDLAY.COMM_DATA.PEN_FLG == 'Y') {
                S000_CALL_LNZSPINI();
                if (pgmRtn) return;
            }
            B035_UEXT_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_LAST_CAL_DT() throws IOException,SQLException,Exception {
        WS_FIRST_RCVD = 'N';
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.KEY.AMT_TYP = 'P';
        T000_READ_LNTRCVD();
        if (pgmRtn) return;
        if (LNRRCVD.DUE_DT > LNCSDLAY.COMM_DATA.VAL_DT) {
            WS_PLAJ_ST_DT = LNRRCVD.DUE_DT;
        } else {
            WS_PLAJ_ST_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        }
    }
    public void R000_REV_CAL() throws IOException,SQLException,Exception {
        WS_FIRST_RCVD = 'N';
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNRRCVD.DUE_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        LNRRCVD.VAL_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        T000_READ_LNTRCVD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_FIRST_RCVD = 'Y';
            IBS.init(SCCGWA, LNCRCAL);
            LNCRCAL.COMM_DATA.FUNC_CODE = 'U';
            LNCRCAL.COMM_DATA.FUNC_TYPE = 'I';
            LNCRCAL.COMM_DATA.LN_AC = LNCSDLAY.COMM_DATA.CONT_NO;
            LNCRCAL.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCRCAL.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCRCAL.COMM_DATA.SUF_NO = "0" + LNCRCAL.COMM_DATA.SUF_NO;
            LNCRCAL.COMM_DATA.VAL_DATE = LNRRCVD.VAL_DT;
            S000_CALL_LNZRCAL();
            if (pgmRtn) return;
        }
    }
    public void R000_INT_CAL() throws IOException,SQLException,Exception {
        if (WS_FIRST_RCVD == 'Y') {
            IBS.init(SCCGWA, LNCICAL);
            LNCICAL.COMM_DATA.FUNC_CODE = 'U';
            LNCICAL.COMM_DATA.FUNC_TYPE = 'I';
            LNCICAL.COMM_DATA.LN_AC = LNCSDLAY.COMM_DATA.CONT_NO;
            LNCICAL.COMM_DATA.SUF_NO = "" + 0;
            JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
            for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
            LNCICAL.COMM_DATA.VAL_DATE = LNCSDLAY.COMM_DATA.VAL_DT;
            S000_CALL_LNZICAL();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_FWD_EXTN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNREXTN.KEY.STATUS = '1';
        LNREXTN.KEY.VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND STATUS = :LNREXTN.KEY.STATUS "
            + "AND VAL_DT > :LNREXTN.KEY.VAL_DT";
        LNTEXTN_RD.fst = true;
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && LNREXTN.KEY.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REPEAT_REC, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_EXTN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        if (LNCSDLAY.COMM_DATA.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            LNREXTN.KEY.STATUS = '1';
            LNREXTN.KEY.EXT_FLG = '1';
        } else {
            LNREXTN.KEY.STATUS = '2';
            LNREXTN.KEY.EXT_FLG = '3';
        }
        LNREXTN.KEY.VAL_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "EXT_FLG = :LNREXTN.KEY.EXT_FLG "
            + "AND CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND STATUS = :LNREXTN.KEY.STATUS "
            + "AND STATUS < > '4' "
            + "AND EXT_FLG >= :LNREXTN.KEY.EXT_FLG";
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_EXIST, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_DEL_LNTEXTN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "SET LNTEXT STS");
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNREXTN.KEY.VAL_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        LNREXTN.KEY.EXT_FLG = '3';
        if (LNCSDLAY.COMM_DATA.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            LNREXTN.KEY.STATUS = '1';
        } else {
            LNREXTN.KEY.STATUS = '2';
        }
        CEP.TRC(SCCGWA, LNREXTN.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        CEP.TRC(SCCGWA, LNREXTN.KEY.EXT_FLG);
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        T000_READUP_LNTEXTN();
        if (pgmRtn) return;
        T000_UPDATE_LNTEXTN();
        if (pgmRtn) return;
    }
    public void B031_WRITE_EXTN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        IBS.init(SCCGWA, LNCREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNREXTN.KEY.VAL_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        LNREXTN.OLD_BAL = LNCCLNQ.DATA.TOT_BAL;
        LNREXTN.OLD_DUE_DT = LNRCONT.MAT_DATE;
        LNREXTN.NEW_DUE_DT = LNCSDLAY.COMM_DATA.DMATU_DT;
        LNREXTN.KEY.EXT_FLG = '3';
        if (LNCSDLAY.COMM_DATA.VAL_DT > SCCGWA.COMM_AREA.AC_DATE) {
            LNREXTN.KEY.STATUS = '1';
        } else {
            LNREXTN.KEY.STATUS = '2';
        }
        LNREXTN.EXT_AMT = LNCCLNQ.DATA.TOT_BAL;
        LNREXTN.RATE_FLG = LNCSDLAY.COMM_DATA.RAT_MTH;
        LNREXTN.NEXT_FLT_DT = LNCSDLAY.COMM_DATA.FLT_DAY;
        LNREXTN.FLT_PERD_UNIT = LNCSDLAY.COMM_DATA.FLP_UNIT;
        if (LNCSDLAY.COMM_DATA.FLPERD.trim().length() == 0) LNREXTN.FLT_PERD = 0;
        else LNREXTN.FLT_PERD = Short.parseShort(LNCSDLAY.COMM_DATA.FLPERD);
        LNREXTN.INT_RATE_TYPE1 = "" + LNCSDLAY.COMM_DATA.IRAT_CD;
        JIBS_tmp_int = LNREXTN.INT_RATE_TYPE1.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNREXTN.INT_RATE_TYPE1 = "0" + LNREXTN.INT_RATE_TYPE1;
        LNREXTN.INT_RATE_CLAS1 = LNCSDLAY.COMM_DATA.RAT_PD;
        LNREXTN.RATE_VARIATION1 = LNCSDLAY.COMM_DATA.RAT_VAR;
        LNREXTN.RATE_PECT1 = LNCSDLAY.COMM_DATA.RAT_PCT;
        LNREXTN.REF_RAT1 = LNCSDLAY.COMM_DATA.RAT_INT;
        LNREXTN.FLOAT_MTH = LNCSDLAY.COMM_DATA.FLT_MTH;
        LNREXTN.ALL_IN_RATE = LNCSDLAY.COMM_DATA.IN_RATE;
        LNCREXTN.FUNC = 'A';
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            S000_CALL_LNZREXTN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNCREXTN.RC.RC_CODE);
        if (LNCREXTN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCREXTN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B025_BK_PAIP_PROC() throws IOException,SQLException,Exception {
        T000_READ_LNTPAIP_LAST();
        if (pgmRtn) return;
        LNCBKRAT.PHS_INST_AMT = LNRPAIP.PHS_INST_AMT;
        LNCBKRAT.PHS_PRIN_AMT = LNRPAIP.PHS_PRIN_AMT;
        LNCBKRAT.PHS_TOT_TERM = LNRPAIP.PHS_TOT_TERM;
        LNCBKRAT.PHS_REM_PRIN_AMT = LNRPAIP.PHS_REM_PRIN_AMT;
        LNCBKRAT.PHS_CAL_TERM = LNRPAIP.PHS_CAL_TERM;
        LNCBKRAT.PHS_CMP_TERM = LNRPAIP.PHS_CMP_TERM;
        LNCBKRAT.CUR_INST_AMT = LNRPAIP.CUR_INST_AMT;
        LNCBKRAT.CUR_INST_IRAT = LNRPAIP.CUR_INST_IRAT;
    }
    public void T000_READ_LNTPAIP_LAST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPAIP);
        LNRPAIP.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNRPAIP.KEY.SUB_CTA_NO = 0;
        LNTPAIP_RD = new DBParm();
        LNTPAIP_RD.TableName = "LNTPAIP";
        LNTPAIP_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTPAIP_RD.fst = true;
        LNTPAIP_RD.order = "PHS_NO DESC";
        IBS.READ(SCCGWA, LNRPAIP, LNTPAIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAIP_EXIST, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B032_UPD_CONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        IBS.init(SCCGWA, LNCRCONT);
        LNCRCONT.FUNC = 'R';
        LNRCONT.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
        WS_LAST_TX_SEQ = LNRCONT.LAST_TX_SEQ;
        WS_LAST_TX_DT = LNRCONT.LAST_TX_DT;
        WS_LAST_F_VAL_DATE = LNRCONT.LAST_F_VAL_DATE;
        LNCRCONT.FUNC = 'U';
        LNRCONT.MAT_DATE = LNCSDLAY.COMM_DATA.DMATU_DT;
        LNRCONT.LAST_TX_SEQ += 1;
        LNRCONT.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNRCONT.LAST_F_VAL_DATE = LNCSPINI.COMM_DATA.VAL_DT;
        S000_CALL_LNZRCONT();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        IBS.init(SCCGWA, LNCRICTL);
        LNCRICTL.FUNC = 'R';
        LNRICTL.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
        LNCRICTL.FUNC = 'U';
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        LNRICTL.CTL_STSW = LNRICTL.CTL_STSW.substring(0, 35 - 1) + "1" + LNRICTL.CTL_STSW.substring(35 + 1 - 1);
        LNRICTL.IC_CAL_DUE_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        S000_CALL_LNZRICTL();
        if (pgmRtn) return;
    }
    public void B070_ADD_TRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'I';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = WS_LAST_TX_DT;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = WS_LAST_F_VAL_DATE;
        WS_LAST_TX_SEQ += 1;
        LNCTRANM.REC_DATA.TR_SEQ = WS_LAST_TX_SEQ;
        LNCTRANM.REC_DATA.TXN_CCY = LNCSDLAY.COMM_DATA.CCY;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B035_UEXT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCUEXT);
        LNCUEXT.CTA_TYP = 'D';
        LNCUEXT.FLG = '1';
        LNCUEXT.CTA_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNCUEXT.CI_NO = LNCSDLAY.COMM_DATA.CI_NO;
        LNCUEXT.BR = LNCSDLAY.COMM_DATA.BOOK_CRE;
        LNCUEXT.PROD_CD = LNCSDLAY.COMM_DATA.PROD_TYP;
        LNCUEXT.CCY = LNRCONT.CCY;
        LNCUEXT.P_AMT = LNCSDLAY.COMM_DATA.AMT;
        LNCUEXT.B_AMT = LNCSDLAY.COMM_DATA.BAL;
        LNCUEXT.OE_DATE = LNRCONT.MAT_DATE;
        LNCUEXT.VAL_DATE = LNCSDLAY.COMM_DATA.VAL_DT;
        LNCUEXT.DLAY_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        LNCUEXT.EE_DATE = LNCSDLAY.COMM_DATA.DMATU_DT;
        LNCUEXT.RAT_INFO.ADJ_FLG = LNCSDLAY.COMM_DATA.ADJ_FLG;
        LNCUEXT.RAT_INFO.IRAT_CD = "" + LNCSDLAY.COMM_DATA.IRAT_CD;
        JIBS_tmp_int = LNCUEXT.RAT_INFO.IRAT_CD.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCUEXT.RAT_INFO.IRAT_CD = "0" + LNCUEXT.RAT_INFO.IRAT_CD;
        LNCUEXT.RAT_INFO.INT_RAT = LNCSDLAY.COMM_DATA.INT_RAT;
        LNCUEXT.RAT_INFO.PEN_RATT = "" + LNCSDLAY.COMM_DATA.PEN_RATT;
        JIBS_tmp_int = LNCUEXT.RAT_INFO.PEN_RATT.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCUEXT.RAT_INFO.PEN_RATT = "0" + LNCUEXT.RAT_INFO.PEN_RATT;
        LNCUEXT.RAT_INFO.PEN_IRAT = LNCSDLAY.COMM_DATA.PEN_IRA;
        LNCUEXT.RAT_INFO.CPND_USE = LNCSDLAY.COMM_DATA.CPND_USE;
        LNCUEXT.RAT_INFO.CPNDRATT = "" + LNCSDLAY.COMM_DATA.CPNDRATT;
        JIBS_tmp_int = LNCUEXT.RAT_INFO.CPNDRATT.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCUEXT.RAT_INFO.CPNDRATT = "0" + LNCUEXT.RAT_INFO.CPNDRATT;
        LNCUEXT.RAT_INFO.CPND_IRAT = LNCSDLAY.COMM_DATA.CPND_IRA;
        LNCUEXT.RAT_INFO.ABUSRATT = "" + LNCSDLAY.COMM_DATA.ABUSRATT;
        JIBS_tmp_int = LNCUEXT.RAT_INFO.ABUSRATT.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) LNCUEXT.RAT_INFO.ABUSRATT = "0" + LNCUEXT.RAT_INFO.ABUSRATT;
        LNCUEXT.RAT_INFO.ABUSIRAT = LNCSDLAY.COMM_DATA.ABUS_IRA;
        S000_CALL_LNZUEXT();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZUEXT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-CONTRACT-EXT", LNCUEXT);
        if (LNCUEXT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUEXT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ);
        if (LNCICIQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICIQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-CAL-BASE", LNCRCAL);
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, LNCICAL.RC.RC_APP, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCONT() throws IOException,SQLException,Exception {
        LNCRCONT.REC_PTR = LNRCONT;
        LNCRCONT.REC_LEN = 416;
        CEP.TRC(SCCGWA, LNRCONT);
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCONT", LNCRCONT);
        if (LNCRCONT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCONT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CTLPM-MAINT", LNCCTLPM);
        if (LNCCTLPM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCTLPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZREXTN() throws IOException,SQLException,Exception {
        LNCREXTN.REC_PTR = LNREXTN;
        LNCREXTN.REC_LEN = 595;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTEXTN", LNCREXTN);
        if (LNCREXTN.RC.RC_CODE != 0) {
            if (LNCREXTN.RETURN_INFO == 'N' 
                || LNCREXTN.RETURN_INFO == 'E') {
                WS_EXIST_FWDH_FLAG = 'N';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCREXTN.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO";
        LNTRCVD_RD.where = "SUB_CTA_NO >= :LNRRCVD.KEY.SUB_CTA_NO "
            + "AND AMT_TYP <= :LNRRCVD.KEY.AMT_TYP";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT DESC";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNRRCVD.DUE_DT = LNRCONT.START_DATE;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 425;
        CEP.TRC(SCCGWA, LNRICTL);
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRFWDH() throws IOException,SQLException,Exception {
        LNCRFWDH.REC_PTR = LNRFWDH;
        LNCRFWDH.REC_LEN = 190;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTFWDH", LNCRFWDH);
        if (LNCRFWDH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRFWDH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRTN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-RATN-MAINT", LNCSRTN);
        CEP.TRC(SCCGWA, LNCSRTN.RC.RC_RTNCODE);
        if (LNCSRTN.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSRTN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-RATL-MAINT", LNCSRTL);
        if (LNCSRTL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSRTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNTRATL() throws IOException,SQLException,Exception {
        LNTRATL_RD = new DBParm();
        LNTRATL_RD.TableName = "LNTRATL";
        LNTRATL_RD.where = "CONTRACT_NO = :LNRRATL.KEY.CONTRACT_NO "
            + "AND OVD_KND = :LNRRATL.KEY.OVD_KND "
            + "AND ACTV_DT = :LNRRATL.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATL, this, LNTRATL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        }
    }
    public void S000_CALL_LNTRATN() throws IOException,SQLException,Exception {
        LNTRATN_RD = new DBParm();
        LNTRATN_RD.TableName = "LNTRATN";
        LNTRATN_RD.where = "CONTRACT_NO = :LNRRATN.KEY.CONTRACT_NO "
            + "AND ACTV_DT = :LNRRATN.KEY.ACTV_DT";
        IBS.READ(SCCGWA, LNRRATN, this, LNTRATN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        }
    }
    public void R000_GET_OLD_RATN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRLOAN);
        LNRLOAN.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        IBS.READ(SCCGWA, LNRLOAN, LNTLOAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LOAN_NFND, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCRATNM);
        LNCRATNM.REC_DATA.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNCRATNM.REC_DATA.KEY.ACTV_DT = LNRLOAN.BRAT_EFF_DT;
        LNCRATNM.FUNC = '3';
        S000_CALL_LNZRATNM();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZRATNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATN-MAINT", LNCRATNM);
        if (LNCRATNM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATNM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPINI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPINI);
        LNCSPINI.COMM_DATA.CONT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNCSPINI.COMM_DATA.CI_NO = LNCSDLAY.COMM_DATA.CI_NO;
        LNCSPINI.COMM_DATA.CI_CNM = LNCSDLAY.COMM_DATA.CI_CNM;
        LNCSPINI.COMM_DATA.BOOK_CRE = LNCSDLAY.COMM_DATA.BOOK_CRE;
        LNCSPINI.COMM_DATA.PROD_CD = LNCSDLAY.COMM_DATA.PROD_TYP;
        LNCSPINI.COMM_DATA.PROD_DES = LNCSDLAY.COMM_DATA.PROD_NM;
        LNCSPINI.COMM_DATA.CCY = LNCSDLAY.COMM_DATA.CCY;
        LNCSPINI.COMM_DATA.AMT = LNCSDLAY.COMM_DATA.AMT;
        LNCSPINI.COMM_DATA.BAL = LNCSDLAY.COMM_DATA.BAL;
        LNCSPINI.COMM_DATA.AC_MATDT = LNCSDLAY.COMM_DATA.MATU_DT;
        LNCSPINI.COMM_DATA.VAL_DT = LNCSDLAY.COMM_DATA.VAL_DT;
        IBS.CALLCPN(SCCGWA, "LN-SRV-LNZSPINI", LNCSPINI);
    }
    public void B050_DEL_EXTN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNREXTN.KEY.EXT_FLG = '3';
        LNREXTN.KEY.STATUS = '4';
        T00_REUPDATE_LNTEXTN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNREXTN.KEY.VAL_DT);
        CEP.TRC(SCCGWA, LNCSDLAY.COMM_DATA.VAL_DT);
        if (WS_EXIST_FWDH_FLAG == 'Y' 
            && LNREXTN.KEY.VAL_DT == LNCSDLAY.COMM_DATA.VAL_DT) {
            T00_DELETE_LNTEXTN();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNREXTN);
        LNREXTN.KEY.CONTRACT_NO = LNCSDLAY.COMM_DATA.CONT_NO;
        LNREXTN.KEY.EXT_FLG = '3';
        LNREXTN.KEY.STATUS = '1';
        T00_REUPDATE_LNTEXTN();
        if (pgmRtn) return;
        if (WS_EXIST_FWDH_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FWDH_NOTFND, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_EXIST_FWDH_FLAG == 'Y') {
            T00_DELETE_LNTEXTN();
            if (pgmRtn) return;
            LNREXTN.KEY.STATUS = '4';
            T00_WRITE_LNTEXTN();
            if (pgmRtn) return;
        }
    }
    public void T00_REUPDATE_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.where = "CONTRACT_NO = :LNREXTN.KEY.CONTRACT_NO "
            + "AND EXT_FLG = :LNREXTN.KEY.EXT_FLG "
            + "AND STATUS = :LNREXTN.KEY.STATUS";
        LNTEXTN_RD.upd = true;
        IBS.READ(SCCGWA, LNREXTN, this, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_EXIST_FWDH_FLAG = 'Y';
        } else {
            WS_EXIST_FWDH_FLAG = 'N';
        }
    }
    public void T00_DELETE_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.DELETE(SCCGWA, LNREXTN, LNTEXTN_RD);
    }
    public void T00_WRITE_LNTEXTN() throws IOException,SQLException,Exception {
        LNREXTN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNREXTN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.WRITE(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_EXTN_DUPKEY, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_RELA() throws IOException,SQLException,Exception {
        LNTRELA_RD = new DBParm();
        LNTRELA_RD.TableName = "LNTRELA";
        LNTRELA_RD.where = "CONTRACT_NO = :LNRRELA.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRRELA.KEY.TYPE";
        IBS.READ(SCCGWA, LNRRELA, this, LNTRELA_RD);
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-RATL-MAINT", LNCRATLM);
        if (LNCRATLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTFWDH_PROC() throws IOException,SQLException,Exception {
        LNTFWDH_RD = new DBParm();
        LNTFWDH_RD.TableName = "LNTFWDH";
        LNTFWDH_RD.where = "CONTRACT_NO = :LNRFWDH.KEY.CONTRACT_NO "
            + "AND TRAN_STS = :LNRFWDH.TRAN_STS "
            + "AND TR_CODE = :LNRFWDH.TR_CODE";
        IBS.READ(SCCGWA, LNRFWDH, this, LNTFWDH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FWSH_EXIST, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZURAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CHACK-RATE", LNCURAT);
        if (LNCURAT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCURAT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AGRE_NOTFND, LNCSDLAY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUP_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        LNTEXTN_RD.upd = true;
        IBS.READ(SCCGWA, LNREXTN, LNTEXTN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RECO_NOTFND, LNCSDLAY.RC);
            LNCREXTN.RETURN_INFO = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_LNTEXTN() throws IOException,SQLException,Exception {
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.DELETE(SCCGWA, LNREXTN, LNTEXTN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        LNREXTN.KEY.STATUS = '4';
        CEP.TRC(SCCGWA, LNREXTN.KEY.STATUS);
        LNREXTN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNREXTN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTEXTN_RD = new DBParm();
        LNTEXTN_RD.TableName = "LNTEXTN";
        IBS.WRITE(SCCGWA, LNREXTN, LNTEXTN_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSDLAY.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCSDLAY=");
            CEP.TRC(SCCGWA, LNCSDLAY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
