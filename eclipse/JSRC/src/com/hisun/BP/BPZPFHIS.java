package com.hisun.BP;

import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.AI.*;
import com.hisun.CM.*;
import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.IB.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPFHIS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTBRTEL_RD;
    DBParm BPTMMOB_RD;
    int FHIST_RLT_AC_NAME_LEN;
    int FHIST_RLT_BK_NM_LEN;
    int FHIST_REMARK_LEN;
    int FHIST_NARRATIVE_LEN;
    DBParm BPTTLT_RD;
    boolean pgmRtn = false;
    String CPN_UPD_FHIST = "BP-R-UPD-FHIST";
    String CPN_INQ_FHIS = "BP-R-INQ-FHIST";
    String PGM_SCZTIME = "SCZTIME";
    String PGM_BPZPRMR = "BPZPRMR";
    String K_PAMC_MMO = "MMO  ";
    String K_TYPE_MMOCK = "MMOCK";
    String WS_COMPONENT_NAME = " ";
    String WS_ERR_MSG = " ";
    String WS_INFO = " ";
    int WS_PART_NO = 0;
    int WS_AC_HASH = 0;
    String WS_H_ACO_AC = " ";
    String WS_DATE_TMP = " ";
    String WS_AC_NO_CN = " ";
    int WS_OPN_BR_CN = 0;
    String WS_OPN_BR_CHN_CN = " ";
    String WS_AC_CHNM_CN = " ";
    String WS_AC_CCY = " ";
    BPZPFHIS_WS_COMPUTE WS_COMPUTE = new BPZPFHIS_WS_COMPUTE();
    char WS_GREAT_FLG = ' ';
    String WS_AGR_NO = " ";
    int WS_AGR_SEQ = 0;
    String WS_FRM_APP = " ";
    String WS_CNTRCT_TYP = " ";
    int WS_AC_MSTBR = 0;
    int WS_AC_OWNER_BK = 0;
    int WS_OWNER_BK = 0;
    int WS_ACDATE = 0;
    short WS_CNT = 0;
    char WS_TBL_FHIS_FLAG = ' ';
    char WS_FHIST_DRCRFLG = ' ';
    char WS_HISTORY_FLAG = ' ';
    char WS_STATUS_FLG = ' ';
    char WS_MMO_CHECK_FLG = ' ';
    char WS_TLT_FLG = ' ';
    char WS_BRTEL_FLG = ' ';
    char WS_MMOB_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCUFHIS BPCUFHIS = new BPCUFHIS();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    SCCWRTSQ SCCWRTSQ = new SCCWRTSQ();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    CICACCU CICACCU = new CICACCU();
    SCCTIME SCCTIME = new SCCTIME();
    BPRPARP BPRPARP = new BPRPARP();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRI CICQACRI = new CICQACRI();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICQACAC CICQACAC = new CICQACAC();
    CMCUFINC CMCUFINC = new CMCUFINC();
    BPRTLT BPRTLT = new BPRTLT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPRBRTEL BPRBRTEL = new BPRBRTEL();
    BPRMMOB BPRMMOB = new BPRMMOB();
    CICSXGOD CICSXGOD = new CICSXGOD();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    TDCACE TDCACE = new TDCACE();
    IBCQINF IBCQINF = new IBCQINF();
    IBCQINFS IBCQINFS = new IBCQINFS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGMSG SCCGMSG;
    SCCGAPV SCCGAPV;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCCWA SCCCWA;
    String LK_MMT = " ";
    BPCPFHIS BPCPFHIS;
    public void MP(SCCGWA SCCGWA, BPCPFHIS BPCPFHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPFHIS = BPCPFHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPFHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        IBS.init(SCCGWA, BPRFHIST);
        IBS.init(SCCGWA, BPCUFHIS);
        IBS.init(SCCGWA, BPCIFHIS);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPFHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_REF_NO);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_VAL_DT);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.CI_NO);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REMARK);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC_DT);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_MMO);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.PROD_CD);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_TX_TOOL);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_CCY);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_TX_TOOL);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.OTH_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.OTH_TX_TOOL);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.SMS_FLG);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY_TYP);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            if (pgmRtn) return;
            B020_PROC_FHIST_CN();
            if (pgmRtn) return;
        } else {
            B020_PROC_FHIST();
            if (pgmRtn) return;
        }
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, "B010-CHECK-INPUT");
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && SCCGWA.COMM_AREA.REVERSAL_IND != 'Y') {
            if (BPCPFHIS.DATA.TX_CCY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_CCY, BPCPFHIS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((BPCPFHIS.DATA.TX_DRCR_FLG != 'D') 
                && (BPCPFHIS.DATA.TX_DRCR_FLG != 'C')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_DRCRFLG_ERR, BPCPFHIS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCPFHIS.DATA.AC.equalsIgnoreCase("0") 
                || BPCPFHIS.DATA.AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EXG_AC_MUST_INPUT, BPCPFHIS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCPFHIS.DATA.ACO_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ACOAC_CANNOT_SPACE, BPCPFHIS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCPFHIS.DATA.AC_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_GT_ACDATE, BPCUFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPFHIS.DATA.TX_CCY.equalsIgnoreCase("156") 
            && BPCPFHIS.DATA.TX_CCY_TYP == ' ') {
            BPCPFHIS.DATA.TX_CCY_TYP = '1';
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY_TYP);
        if ((BPCPFHIS.DATA.TX_CCY_TYP == ' ' 
            && BPCPFHIS.DATA.TX_CCY_TYP == '1' 
            && BPCPFHIS.DATA.TX_CCY_TYP == '2')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_CCY_TYPE;
            WS_INFO = "" + BPCPFHIS.DATA.TX_CCY_TYP;
            JIBS_tmp_int = WS_INFO.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) WS_INFO = "0" + WS_INFO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.TL_ID.trim().length() > 0) {
            IBS.init(SCCGWA, BPRTLT);
            BPRTLT.KEY.TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_READ_BPTTLT();
            if (pgmRtn) return;
            if (WS_TLT_FLG == 'Y') {
                CEP.TRC(SCCGWA, BPRTLT.CI_NO);
                if (BPRTLT.CI_NO.equalsIgnoreCase(BPCPFHIS.DATA.CI_NO) 
                    && BPCPFHIS.DATA.CI_NO.trim().length() > 0 
                    && BPRTLT.CI_NO.trim().length() > 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.M_TLR_NOT_MOD_OWNIFO;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B020_PROC_FHIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.SUMUP_FLG);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && SCCGWA.COMM_AREA.REVERSAL_IND != 'Y') {
            B030_ADD_REC();
            if (pgmRtn) return;
        } else {
            B030_MOD_ORI_TXN_REC();
            if (pgmRtn) return;
            B030_ADD_REC();
            if (pgmRtn) return;
        }
        B030_SEND_REC();
        if (pgmRtn) return;
        S000_CALL_SCOWRTSQ();
        if (pgmRtn) return;
    }
    public void B020_PROC_FHIST_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.SUMUP_FLG);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && SCCGWA.COMM_AREA.REVERSAL_IND != 'Y') {
            B030_ADD_REC_CN();
            if (pgmRtn) return;
        } else {
            B030_MOD_ORI_TXN_REC_CN();
            if (pgmRtn) return;
            B030_ADD_REC_CN();
            if (pgmRtn) return;
        }
        B050_SEND_MSG_CN();
        if (pgmRtn) return;
    }
    public void B050_SEND_MSG_CN() throws IOException,SQLException,Exception {
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && BPRFHIST.PRINT_FLG == 'Y') {
            if (BPCPFHIS.DATA.SMS_FLG != 'N') {
                IBS.init(SCCGWA, CICQACRI);
                CICQACRI.DATA.AGR_NO = BPRFHIST.KEY.AC;
                CICQACRI.FUNC = 'A';
                S000_CALL_CIZQACRI_CN();
                if (pgmRtn) return;
                if (CICQACRI.O_DATA.O_SMS_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "SEND MESSAGE");
                    IBS.init(SCCGWA, SCCWRMSG);
                    B030_SEND_REC_CN();
                    if (pgmRtn) return;
                    S000_CALL_SCZWRMSG_CN();
                    if (pgmRtn) return;
                }
            }
        }
        if (!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            && BPRFHIST.DISPLAY_IND != 'N') {
            B031_GET_MSTBR();
            if (pgmRtn) return;
            B032_CHECK_GREAT();
            if (pgmRtn) return;
            if (WS_GREAT_FLG == 'Y') {
                B033_SEND_MSG_GREAT();
                if (pgmRtn) return;
            }
        }
    }
    public void B031_GET_MSTBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = BPRFHIST.ACO_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        WS_AGR_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        WS_FRM_APP = CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC;
        WS_CNTRCT_TYP = CICQACAC.O_DATA.O_ACR_DATA.O_CNTRCT_TYP;
        if (WS_FRM_APP.equalsIgnoreCase("DD")) {
            CEP.TRC(SCCGWA, "DD");
            IBS.init(SCCGWA, DDCIQBAL);
            DDCIQBAL.DATA.AC = WS_AGR_NO;
            DDCIQBAL.DATA.CCY = BPRFHIST.TX_CCY;
            DDCIQBAL.DATA.CCY_TYPE = BPRFHIST.TX_CCY_TYPE;
            IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL, true);
            WS_AC_MSTBR = DDCIQBAL.DATA.OWNER_BRDP;
        }
        if (WS_FRM_APP.equalsIgnoreCase("TD")) {
            if (WS_CNTRCT_TYP.equalsIgnoreCase("043") 
                || WS_CNTRCT_TYP.equalsIgnoreCase("044")) {
                WS_AC_MSTBR = CICQACAC.O_DATA.O_ACR_DATA.O_OPN_BR_ACR;
            } else {
                CEP.TRC(SCCGWA, "TD");
                IBS.init(SCCGWA, TDCACE);
                TDCACE.PAGE_INF.AC_NO = WS_AGR_NO;
                TDCACE.PAGE_INF.I_AC_SEQ = WS_AGR_SEQ;
                IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
                WS_AC_MSTBR = TDCACE.DATA[1-1].CHE_BR;
            }
            if (WS_AC_MSTBR != 0) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = WS_AC_MSTBR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                CEP.TRC(SCCGWA, BPCPQORG.BR);
                CEP.TRC(SCCGWA, BPCPQORG.BBR);
                if (BPCPQORG.ATTR != '2') {
                    if (BPCPQORG.ATTR == '3') {
                        if (BPCPQORG.BBR != 0) {
                            IBS.init(SCCGWA, BPCPQORG);
                            BPCPQORG.BR = BPCPQORG.BBR;
                            S000_CALL_BPZPQORG();
                            if (pgmRtn) return;
                            if (BPCPQORG.ATTR != '2') {
                                CEP.ERR(SCCGWA, "AI3868");
                            } else {
                                WS_AC_MSTBR = BPCPQORG.BBR;
                            }
                        } else {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR);
                        }
                    } else {
                        CEP.ERR(SCCGWA, "AI3868");
                    }
                }
            }
        }
        if (WS_FRM_APP.equalsIgnoreCase("IB") 
            && WS_CNTRCT_TYP.equalsIgnoreCase("IBDD")) {
            CEP.TRC(SCCGWA, "IBDD");
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = WS_AGR_NO;
            IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF, true);
            if (IBCQINF.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
            WS_AC_MSTBR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        }
        if (WS_FRM_APP.equalsIgnoreCase("IB") 
            && WS_CNTRCT_TYP.equalsIgnoreCase("IBTD")) {
            CEP.TRC(SCCGWA, "IBTD");
            IBS.init(SCCGWA, IBCQINFS);
            IBCQINFS.PRIM_AC_NO = WS_AGR_NO;
            IBCQINFS.SEQ_NO = WS_AGR_SEQ;
            IBS.CALLCPN(SCCGWA, "IB-IBZQINFS", IBCQINFS, true);
            if (IBCQINFS.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFS.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
            WS_AC_MSTBR = IBCQINFS.POST_CTR;
        }
        CEP.TRC(SCCGWA, WS_AC_MSTBR);
    }
    public void B032_CHECK_GREAT() throws IOException,SQLException,Exception {
        BPRBRTEL.BR = WS_AC_MSTBR;
        BPTBRTEL_RD = new DBParm();
        BPTBRTEL_RD.TableName = "BPTBRTEL";
        BPTBRTEL_RD.where = "BR = :BPRBRTEL.BR";
        BPTBRTEL_RD.fst = true;
        BPTBRTEL_RD.order = "BR";
        IBS.READ(SCCGWA, BPRBRTEL, this, BPTBRTEL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BRTEL_FLG = 'Y';
        } else {
            WS_BRTEL_FLG = 'N';
        }
        IBS.init(SCCGWA, BPRMMOB);
        CEP.TRC(SCCGWA, BPRFHIST.TX_MMO);
        BPRMMOB.KEY.TX_MMO = BPRFHIST.TX_MMO;
        BPTMMOB_RD = new DBParm();
        BPTMMOB_RD.TableName = "BPTMMOB";
        BPTMMOB_RD.where = "TX_MMO = :BPRMMOB.KEY.TX_MMO";
        BPTMMOB_RD.fst = true;
        BPTMMOB_RD.order = "TX_MMO";
        IBS.READ(SCCGWA, BPRMMOB, this, BPTMMOB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MMOB_FLG = 'Y';
        } else {
            WS_MMOB_FLG = 'N';
        }
        WS_GREAT_FLG = 'N';
        if (WS_BRTEL_FLG == 'Y' 
            && BPRBRTEL.OPEN_FLG == 'Y' 
            && BPRFHIST.TX_AMT >= BPRBRTEL.MIN_AMT 
            && SCCGWA.COMM_AREA.TR_TIME >= BPRBRTEL.START_TIME 
            && SCCGWA.COMM_AREA.TR_TIME <= BPRBRTEL.END_TIME 
            && WS_MMOB_FLG == 'Y' 
            && BPRMMOB.BRD_FLG == 'Y') {
            WS_GREAT_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_GREAT_FLG);
    }
    public void B033_SEND_MSG_GREAT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCWRMSG);
        IBS.init(SCCGWA, CICSXGOD);
        CICSXGOD.INPUT_DATA.IN_CI = BPRFHIST.CI_NO;
        IBS.CALLCPN(SCCGWA, "CI-GET-INFO", CICSXGOD);
    }
    public void B030_ADD_REC_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        B050_TRANS_DATA_CN();
        if (pgmRtn) return;
        if (BPCPFHIS.DATA.DISPLAY_IND != 'N') {
            B045_FIN_CHECK_CN();
            if (pgmRtn) return;
        }
        B031_CREATE_REC_CN();
        if (pgmRtn) return;
    }
    public void B050_TRANS_DATA_CN() throws IOException,SQLException,Exception {
        BPRFHIST.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_H_ACO_AC = BPCPFHIS.DATA.ACO_AC;
        R000_GET_PART_NO();
        if (pgmRtn) return;
        BPRFHIST.KEY.PART_NO = WS_PART_NO;
        if (BPCPFHIS.DATA.JRNNO > 0) {
            BPRFHIST.KEY.JRNNO = BPCPFHIS.DATA.JRNNO;
        } else {
            BPRFHIST.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        }
        GWA_BP_AREA.FHIS_CUR_SEQ = (short) (GWA_BP_AREA.FHIS_CUR_SEQ + 1);
        BPRFHIST.KEY.JRN_SEQ = GWA_BP_AREA.FHIS_CUR_SEQ;
        BPRFHIST.TX_CHNL_JRN = GWA_SC_AREA.REQ_CHNL_JRN;
        BPRFHIST.TX_REQFM = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFHIST.TX_SYS_JRN = GWA_SC_AREA.REQ_SYS_JRN;
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, GWA_BP_AREA.FHIS_CUR_SEQ);
        if (BPCPFHIS.DATA.VCHNO.trim().length() == 0) {
            BPRFHIST.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        } else {
            BPRFHIST.VCHNO = BPCPFHIS.DATA.VCHNO;
        }
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT") 
            || SCCGWA.COMM_AREA.TR_DATE == 0) {
            S00_GET_TIME();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCTIME.YYYYMMDD);
            CEP.TRC(SCCGWA, SCCTIME.HHMMSS);
            BPRFHIST.TX_DT = SCCTIME.YYYYMMDD;
            BPRFHIST.TX_TM = SCCTIME.HHMMSS;
        } else {
            BPRFHIST.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
            BPRFHIST.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_DATE);
            CEP.TRC(SCCGWA, BPRFHIST.TX_DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
            CEP.TRC(SCCGWA, BPRFHIST.TX_TM);
        }
        BPRFHIST.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, BPRFHIST.TX_BR);
        BPRFHIST.TX_DP = SCCGWA.COMM_AREA.BR_DP.TR_DEP;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_DEP);
        CEP.TRC(SCCGWA, BPRFHIST.TX_DP);
        BPRFHIST.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
        BPRFHIST.TX_REQ_CHNL = SCCGWA.COMM_AREA.REQ_CHNL2;
        BPRFHIST.TX_CHNL_DTL = SCCGWA.COMM_AREA.CHNL_DTL;
        BPRFHIST.TX_REQFM = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFHIST.APP_MMO = SCCGWA.COMM_AREA.AP_EXT_MMO;
        BPRFHIST.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (BPCPFHIS.DATA.PROD_CD.trim().length() > 0) {
            BPRFHIST.PROD_CD = BPCPFHIS.DATA.PROD_CD;
        } else {
            BPRFHIST.PROD_CD = SCCGWA.COMM_AREA.PROD_CODE;
        }
        BPRFHIST.PRDMO_CD = BPCPFHIS.DATA.PRDMO_CD;
        BPRFHIST.REF_NO = BPCPFHIS.DATA.REF_NO;
        BPRFHIST.BV_CODE = BPCPFHIS.DATA.BV_CODE;
        BPRFHIST.HEAD_NO = BPCPFHIS.DATA.HEAD_NO;
        BPRFHIST.BV_NO = BPCPFHIS.DATA.BV_NO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO);
        if (BPCPFHIS.DATA.CI_NO.equalsIgnoreCase("0") 
            || BPCPFHIS.DATA.CI_NO.trim().length() == 0) {
            BPRFHIST.CI_NO = CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO;
        } else {
            BPRFHIST.CI_NO = BPCPFHIS.DATA.CI_NO;
        }
        BPRFHIST.KEY.AC = BPCPFHIS.DATA.AC;
        BPRFHIST.ACO_AC = BPCPFHIS.DATA.ACO_AC;
        BPRFHIST.TX_TOOL = BPCPFHIS.DATA.TX_TOOL;
        BPRFHIST.OTH_AC = BPCPFHIS.DATA.OTH_AC;
        CEP.TRC(SCCGWA, BPRFHIST.OTH_AC);
        BPRFHIST.OTH_TX_TOOL = BPCPFHIS.DATA.OTH_TX_TOOL;
        CEP.TRC(SCCGWA, BPRFHIST.OTH_TX_TOOL);
        BPRFHIST.RLT_AC = BPCPFHIS.DATA.RLT_AC;
        CEP.TRC(SCCGWA, BPRFHIST.RLT_AC);
        BPRFHIST.RLT_TX_TOOL = BPCPFHIS.DATA.RLT_TX_TOOL;
        CEP.TRC(SCCGWA, BPRFHIST.RLT_TX_TOOL);
        if (BPCPFHIS.DATA.RLT_TX_TOOL.trim().length() > 0 
            && BPCPFHIS.DATA.RLT_AC.trim().length() == 0) {
            CEP.TRC(SCCGWA, "NO RLT AC");
            BPRFHIST.RLT_AC = BPCPFHIS.DATA.RLT_TX_TOOL;
            CEP.TRC(SCCGWA, BPRFHIST.RLT_AC);
        }
        BPRFHIST.RLT_AC_NAME = BPCPFHIS.DATA.RLT_AC_NAME;
        FHIST_RLT_AC_NAME_LEN = BPRFHIST.RLT_AC_NAME.length();
        BPRFHIST.RLT_BANK = BPCPFHIS.DATA.RLT_BANK;
        CEP.TRC(SCCGWA, BPRFHIST.RLT_BANK);
        BPRFHIST.RLT_BK_NM = BPCPFHIS.DATA.RLT_BK_NM;
        FHIST_RLT_BK_NM_LEN = BPRFHIST.RLT_BK_NM.length();
        BPRFHIST.RLT_REF_NO = BPCPFHIS.DATA.RLT_REF_NO;
        CEP.TRC(SCCGWA, BPRFHIST.RLT_REF_NO);
        BPRFHIST.RLT_CCY = BPCPFHIS.DATA.RLT_CCY;
        CEP.TRC(SCCGWA, BPRFHIST.RLT_CCY);
        BPRFHIST.TX_CCY = BPCPFHIS.DATA.TX_CCY;
        CEP.TRC(SCCGWA, BPRFHIST.TX_CCY);
        BPRFHIST.TX_CCY_TYPE = BPCPFHIS.DATA.TX_CCY_TYP;
        CEP.TRC(SCCGWA, BPRFHIST.TX_CCY_TYPE);
        BPRFHIST.TX_TYPE = BPCPFHIS.DATA.TX_TYPE;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            if (BPCPFHIS.DATA.TX_MMO.equalsIgnoreCase("A036") 
                || BPCPFHIS.DATA.TX_MMO.equalsIgnoreCase("A037") 
                || BPCPFHIS.DATA.TX_MMO.equalsIgnoreCase("G111") 
                || BPCPFHIS.DATA.TX_MMO.equalsIgnoreCase("G112")) {
                BPRFHIST.TX_MMO = BPCPFHIS.DATA.TX_MMO;
            } else {
                BPRFHIST.TX_MMO = "G004";
            }
        } else {
            if (BPCPFHIS.DATA.TX_MMO.trim().length() > 0) {
                BPRFHIST.TX_MMO = BPCPFHIS.DATA.TX_MMO;
            } else {
                BPRFHIST.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
            }
        }
        BPRFHIST.PV_T_TYP = BPCPFHIS.DATA.PV_T_TYP;
        BPRFHIST.TX_VAL_DT = BPCPFHIS.DATA.TX_VAL_DT;
        BPRFHIST.SUMUP_FLG = BPCPFHIS.DATA.SUMUP_FLG;
        if (BPCPFHIS.DATA.PRINT_IND != 'N') {
            BPRFHIST.PRINT_FLG = 'Y';
        } else {
            BPRFHIST.PRINT_FLG = 'N';
        }
        if (BPCPFHIS.DATA.DISPLAY_IND != 'N') {
            BPRFHIST.DISPLAY_IND = 'Y';
        } else {
            BPRFHIST.DISPLAY_IND = 'N';
        }
        WS_COMPUTE.WS_CPU1_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_COMPUTE.WS_CPU1_DATE+"", WS_COMPUTE.REDEFINES15);
        WS_COMPUTE.WS_CPU2_DATE = BPCPFHIS.DATA.TX_VAL_DT;
        IBS.CPY2CLS(SCCGWA, WS_COMPUTE.WS_CPU2_DATE+"", WS_COMPUTE.REDEFINES20);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPRFHIST.TX_STS = 'R';
            BPRFHIST.REMARK = GWA_BP_AREA.CANCEL_AREA.CAN_REMARK;
            FHIST_REMARK_LEN = BPRFHIST.REMARK.length();
            BPRFHIST.ORG_AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRFHIST.ORG_JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            CEP.TRC(SCCGWA, WS_FHIST_DRCRFLG);
            if (WS_COMPUTE.REDEFINES15.WS_CPU1_YYYY == WS_COMPUTE.REDEFINES20.WS_CPU2_YYYY) {
                BPRFHIST.DRCRFLG = BPCPFHIS.DATA.TX_DRCR_FLG;
                BPRFHIST.TX_AMT = BPCPFHIS.DATA.TX_AMT * ( -1 );
            } else {
                if (WS_FHIST_DRCRFLG == 'D') {
                    BPRFHIST.DRCRFLG = 'C';
                }
                if (WS_FHIST_DRCRFLG == 'C') {
                    BPRFHIST.DRCRFLG = 'D';
                }
                BPRFHIST.TX_AMT = BPCPFHIS.DATA.TX_AMT;
            }
            CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
            CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
        } else {
            BPRFHIST.DRCRFLG = BPCPFHIS.DATA.TX_DRCR_FLG;
            BPRFHIST.TX_AMT = BPCPFHIS.DATA.TX_AMT;
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
            BPRFHIST.TX_STS = 'N';
            BPRFHIST.REMARK = BPCPFHIS.DATA.REMARK;
            FHIST_REMARK_LEN = BPRFHIST.REMARK.length();
        }
        CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
        CEP.TRC(SCCGWA, BPRFHIST.PRINT_FLG);
        CEP.TRC(SCCGWA, BPRFHIST.REMARK);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRFHIST.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
            if (BPCPFHIS.DATA.NARRATIVE.trim().length() > 0) {
                if (BPRFHIST.NARRATIVE == null) BPRFHIST.NARRATIVE = "";
                JIBS_tmp_int = BPRFHIST.NARRATIVE.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) BPRFHIST.NARRATIVE += " ";
                BPRFHIST.NARRATIVE = "REV " + BPRFHIST.NARRATIVE.substring(4);
                if (BPRFHIST.NARRATIVE == null) BPRFHIST.NARRATIVE = "";
                JIBS_tmp_int = BPRFHIST.NARRATIVE.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) BPRFHIST.NARRATIVE += " ";
                if (BPCPFHIS.DATA.NARRATIVE == null) BPCPFHIS.DATA.NARRATIVE = "";
                JIBS_tmp_int = BPCPFHIS.DATA.NARRATIVE.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) BPCPFHIS.DATA.NARRATIVE += " ";
                BPRFHIST.NARRATIVE = BPRFHIST.NARRATIVE.substring(0, 5 - 1) + BPCPFHIS.DATA.NARRATIVE + BPRFHIST.NARRATIVE.substring(5 + BPCPFHIS.DATA.NARRATIVE.length() - 1);
                FHIST_NARRATIVE_LEN = BPRFHIST.NARRATIVE.length();
                CEP.TRC(SCCGWA, BPRFHIST.NARRATIVE);
            }
        } else {
            BPRFHIST.NARRATIVE = BPCPFHIS.DATA.NARRATIVE;
            FHIST_NARRATIVE_LEN = BPRFHIST.NARRATIVE.length();
            CEP.TRC(SCCGWA, BPRFHIST.NARRATIVE);
        }
        BPRFHIST.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRFHIST.MAKER = BPCPFHIS.DATA.TX_MAKER;
        CEP.TRC(SCCGWA, BPRFHIST.MAKER);
        BPRFHIST.SUP1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRFHIST.SUP2 = SCCGWA.COMM_AREA.SUP2_ID;
        CEP.TRC(SCCGWA, BPRFHIST.TX_TLR);
        CEP.TRC(SCCGWA, BPRFHIST.MAKER);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP2_ID);
        CEP.TRC(SCCGWA, BPRFHIST.SUP1);
        CEP.TRC(SCCGWA, BPRFHIST.SUP2);
        BPRFHIST.COM_PROD = BPCPFHIS.DATA.COM_PROD;
        if (BPCPFHIS.DATA.TX_TYPE == ' ') {
            BPRFHIST.TX_TYPE = 'T';
        } else {
            BPRFHIST.TX_TYPE = BPCPFHIS.DATA.TX_TYPE;
        }
        CEP.TRC(SCCGWA, BPRFHIST.HEAD_NO);
        CEP.TRC(SCCGWA, BPRFHIST.BV_CODE);
        BPRFHIST.UPDATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_AC_NO_CN = BPCPFHIS.DATA.RLT_AC;
        R000_GET_AC_INFO_CN();
        if (pgmRtn) return;
        if (BPCPFHIS.DATA.RLT_AC_NAME.trim().length() == 0) {
            BPRFHIST.RLT_AC_NAME = WS_AC_CHNM_CN;
            FHIST_RLT_AC_NAME_LEN = BPRFHIST.RLT_AC_NAME.length();
        }
        if (BPCPFHIS.DATA.RLT_BANK.trim().length() == 0 
            || BPCPFHIS.DATA.RLT_BANK.equalsIgnoreCase("0") 
            || BPCPFHIS.DATA.RLT_BANK.equalsIgnoreCase("000000")) {
            BPRFHIST.RLT_BANK = "" + WS_OPN_BR_CN;
            JIBS_tmp_int = BPRFHIST.RLT_BANK.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPRFHIST.RLT_BANK = "0" + BPRFHIST.RLT_BANK;
        }
        if (BPCPFHIS.DATA.RLT_BK_NM.trim().length() == 0) {
            BPRFHIST.RLT_BK_NM = WS_OPN_BR_CHN_CN;
            FHIST_RLT_BK_NM_LEN = BPRFHIST.RLT_BK_NM.length();
        }
        if (BPRFHIST.RLT_BANK.equalsIgnoreCase("000000")) {
            BPRFHIST.RLT_BANK = " ";
        }
        CEP.TRC(SCCGWA, "***1 CITY-FLG CHECK ***");
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.CITY_FLG);
        if (BPCPFHIS.DATA.CITY_FLG != ' ') {
            BPRFHIST.CITY_FLG = BPCPFHIS.DATA.CITY_FLG;
        } else {
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
            WS_AC_NO_CN = BPCPFHIS.DATA.AC;
            R000_GET_AC_INFO_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, WS_OPN_BR_CN);
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == WS_OPN_BR_CN) {
                BPRFHIST.CITY_FLG = '0';
            } else {
                IBS.init(SCCGWA, BPCPQORG);
                WS_OWNER_BK = 0;
                BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                WS_OWNER_BK = BPCPQORG.BRANCH_BR;
                CEP.TRC(SCCGWA, WS_OWNER_BK);
                CEP.TRC(SCCGWA, WS_AC_OWNER_BK);
                if (WS_OWNER_BK != WS_AC_OWNER_BK) {
                    BPRFHIST.CITY_FLG = '1';
                } else {
                    BPRFHIST.CITY_FLG = '0';
                }
            }
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.ST_FIL);
        BPRFHIST.ST_FIL = BPCPFHIS.DATA.ST_FIL;
    }
    public void B045_FIN_CHECK_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = BPCPFHIS.DATA.ACO_AC;
        CICQACAC.FUNC = 'A';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        WS_AC_CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL == null) CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL = "";
        JIBS_tmp_int = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL += " ";
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_CTL.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, CMCUFINC);
            CMCUFINC.AC_DATE = BPRFHIST.KEY.AC_DT;
            CMCUFINC.JRN_NO = BPRFHIST.KEY.JRNNO;
            CMCUFINC.JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
            CMCUFINC.CARD_NO = BPRFHIST.TX_TOOL;
            CMCUFINC.AC_NO = BPRFHIST.KEY.AC;
            CMCUFINC.AC_CCY = WS_AC_CCY;
            CMCUFINC.AVL_BAL = BPCPFHIS.DATA.VAL_BAL;
            CMCUFINC.BV_CD = BPRFHIST.BV_CODE;
            CMCUFINC.BV_NO = BPRFHIST.BV_NO;
            CMCUFINC.DC_FLG = BPRFHIST.DRCRFLG;
            CMCUFINC.CT_FLG = BPRFHIST.TX_TYPE;
            CMCUFINC.TR_AMT = BPRFHIST.TX_AMT;
            CMCUFINC.TR_CCY = BPRFHIST.TX_CCY;
            CMCUFINC.OPP_AC = BPRFHIST.OTH_AC;
            CMCUFINC.OPP_CARD_NO = BPRFHIST.OTH_TX_TOOL;
            CMCUFINC.CCY_TYPE = BPRFHIST.TX_CCY_TYPE;
            WS_AC_NO_CN = " ";
            WS_AC_CHNM_CN = " ";
            WS_AC_NO_CN = BPCPFHIS.DATA.RLT_AC;
            R000_GET_AC_INFO_CN();
            if (pgmRtn) return;
            CMCUFINC.OPP_NAME = WS_AC_CHNM_CN;
            CMCUFINC.RLT_AC = BPRFHIST.RLT_AC;
            CMCUFINC.RLT_CARD_NO = BPRFHIST.RLT_TX_TOOL;
            if (FHIST_RLT_AC_NAME_LEN == 0) {
                FHIST_RLT_AC_NAME_LEN = 1;
            }
            if (BPRFHIST.RLT_AC_NAME == null) BPRFHIST.RLT_AC_NAME = "";
            JIBS_tmp_int = BPRFHIST.RLT_AC_NAME.length();
            for (int i=0;i<252-JIBS_tmp_int;i++) BPRFHIST.RLT_AC_NAME += " ";
            CMCUFINC.RLT_NAME = BPRFHIST.RLT_AC_NAME.substring(0, FHIST_RLT_AC_NAME_LEN);
            CMCUFINC.TX_MMO = BPRFHIST.TX_MMO;
            S000_CALL_CMZUFINC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        CEP.TRC(SCCGWA, CICQACAC.RC);
    }
    public void S000_CALL_CMZUFINC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-U-CMZUFINC", CMCUFINC);
        if (CMCUFINC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCUFINC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void R000_GET_AC_INFO_CN() throws IOException,SQLException,Exception {
        WS_AC_CHNM_CN = " ";
        WS_OPN_BR_CHN_CN = " ";
        WS_OPN_BR_CN = 0;
        WS_AC_OWNER_BK = 0;
        CEP.TRC(SCCGWA, WS_AC_NO_CN);
        if (WS_AC_NO_CN.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_AC_NO_CN;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
            if (CICQACRI.RC.RC_CODE == 0) {
                CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
                if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                    IBS.init(SCCGWA, AICPQMIB);
                    AICPQMIB.INPUT_DATA.AC = CICQACRI.DATA.AGR_NO;
                    S000_CALL_AIZPQMIB_CN();
                    if (pgmRtn) return;
                    if (AICPQMIB.OUTPUT_DATA.CHS_NM.trim().length() > 0) {
                        WS_AC_CHNM_CN = AICPQMIB.OUTPUT_DATA.CHS_NM;
                    } else if (AICPQMIB.OUTPUT_DATA.ENG_NM.trim().length() > 0) {
                        WS_AC_CHNM_CN = AICPQMIB.OUTPUT_DATA.ENG_NM;
                    } else {
                        WS_AC_CHNM_CN = " ";
                    }
                    CEP.TRC(SCCGWA, AICPQMIB.INPUT_DATA.BR);
                    WS_OPN_BR_CN = AICPQMIB.INPUT_DATA.BR;
                    CEP.TRC(SCCGWA, WS_OPN_BR_CN);
                } else {
                    if (CICQACRI.O_DATA.O_AC_CNM.trim().length() > 0) {
                        WS_AC_CHNM_CN = CICQACRI.O_DATA.O_AC_CNM;
                    } else if (CICQACRI.O_DATA.O_AC_ENM.trim().length() > 0) {
                        WS_AC_CHNM_CN = CICQACRI.O_DATA.O_AC_ENM;
                    } else {
                        WS_AC_CHNM_CN = " ";
                    }
                    CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
                    WS_OPN_BR_CN = CICQACRI.O_DATA.O_OPN_BR;
                    CEP.TRC(SCCGWA, WS_OPN_BR_CN);
                }
                CEP.TRC(SCCGWA, WS_AC_CHNM_CN);
                CEP.TRC(SCCGWA, WS_OPN_BR_CN);
                if (WS_OPN_BR_CN != 0) {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
                    BPCPQORG.BR = WS_OPN_BR_CN;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    WS_OPN_BR_CHN_CN = BPCPQORG.CHN_NM;
                    CEP.TRC(SCCGWA, WS_OPN_BR_CHN_CN);
                    CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
                    WS_AC_OWNER_BK = BPCPQORG.BRANCH_BR;
                    CEP.TRC(SCCGWA, WS_AC_OWNER_BK);
                }
            }
        }
    }
    public void S000_CALL_CIZQACRI_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "CI-INQ-ACR-INF";
        SCCCALL.COMMAREA_PTR = CICQACRI;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_AIZPQMIB_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "AI-P-INQ-MIB";
        SCCCALL.COMMAREA_PTR = AICPQMIB;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-P-INQ-ORG";
        SCCCALL.COMMAREA_PTR = BPCPQORG;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void B030_MOD_ORI_TXN_REC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-MOD-ORI-TXN-REC-CN");
        IBS.init(SCCGWA, BPCUFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        WS_HISTORY_FLAG = ' ';
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_STM_IND);
        BPRFHIST.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRFHIST.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '3';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '4';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPFHIS.RC);
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "ABC0000000000000000000000");
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
        CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
        CEP.TRC(SCCGWA, BPRFHIST.TX_CCY);
        CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC);
        CEP.TRC(SCCGWA, BPRFHIST.REF_NO);
        CEP.TRC(SCCGWA, WS_HISTORY_FLAG);
        WS_STATUS_FLG = 'C';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE) 
            && WS_HISTORY_FLAG != 'Y') {
            WS_HISTORY_FLAG = 'N';
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
            CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY);
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.OTH_AC);
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
            CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
            CEP.TRC(SCCGWA, BPRFHIST.TX_CCY);
            CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
            CEP.TRC(SCCGWA, BPRFHIST.KEY.AC);
            CEP.TRC(SCCGWA, BPRFHIST.REF_NO);
            CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
            CEP.TRC(SCCGWA, BPRFHIST.OTH_AC);
            CEP.TRC(SCCGWA, BPRFHIST.RLT_AC);
            if (SCCGWA.COMM_AREA.REVERSAL_IND != 'Y') {
                if (BPRFHIST.DRCRFLG == BPCPFHIS.DATA.TX_DRCR_FLG 
                    && BPRFHIST.TX_CCY.equalsIgnoreCase(BPCPFHIS.DATA.TX_CCY) 
                    && BPRFHIST.TX_AMT == BPCPFHIS.DATA.TX_AMT 
                    && BPRFHIST.KEY.AC.equalsIgnoreCase(BPCPFHIS.DATA.AC) 
                    && BPRFHIST.REF_NO.equalsIgnoreCase(BPCPFHIS.DATA.REF_NO) 
                    && BPRFHIST.OTH_AC.equalsIgnoreCase(BPCPFHIS.DATA.OTH_AC) 
                    && BPRFHIST.TX_STS == 'N') {
                    CEP.TRC(SCCGWA, "NOT FEE");
                    WS_HISTORY_FLAG = 'Y';
                    WS_STATUS_FLG = 'N';
                }
            } else {
                if (BPRFHIST.DRCRFLG == BPCPFHIS.DATA.TX_DRCR_FLG 
                    && BPRFHIST.TX_CCY.equalsIgnoreCase(BPCPFHIS.DATA.TX_CCY) 
                    && BPRFHIST.TX_AMT == BPCPFHIS.DATA.TX_AMT 
                    && BPRFHIST.KEY.AC.equalsIgnoreCase(BPCPFHIS.DATA.AC) 
                    && BPRFHIST.OTH_AC.equalsIgnoreCase(BPCPFHIS.DATA.OTH_AC) 
                    && BPRFHIST.REF_NO.equalsIgnoreCase(BPCPFHIS.DATA.REF_NO)) {
                    CEP.TRC(SCCGWA, "THAT FEE");
                    WS_HISTORY_FLAG = 'Y';
                    WS_STATUS_FLG = 'N';
                }
            }
            CEP.TRC(SCCGWA, WS_HISTORY_FLAG);
            if (WS_HISTORY_FLAG == 'Y') {
                if (BPRFHIST.TX_STS == 'C') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ALREADY_CANCEL, BPCPFHIS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                BPRFHIST.TX_STS = 'C';
                BPRFHIST.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRFHIST.UPDATE_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPCUFHIS.DATA.POINTER = BPRFHIST;
                BPCUFHIS.DATA.REC_LEN = 690;
                BPCUFHIS.DATA.FUNC = '2';
                S000_CALL_BPZUFHIS();
                if (pgmRtn) return;
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCUFHIS.RC);
                WS_FHIST_DRCRFLG = BPRFHIST.DRCRFLG;
                CEP.TRC(SCCGWA, "YYC:");
                CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
                CEP.TRC(SCCGWA, WS_FHIST_DRCRFLG);
            }
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            BPCUFHIS.DATA.FUNC = '4';
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("BP1015")) {
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_STATUS_FLG);
        if (WS_STATUS_FLG == 'C') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NO_SUIT_RECORD, BPCPFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B031_CREATE_REC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B031-CREATE-REC-CN");
        IBS.init(SCCGWA, BPCUFHIS);
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '1';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
    }
    public void B030_SUMUP_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-SUMUP-REC");
        IBS.init(SCCGWA, BPCUFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIST.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFHIST.KEY.AC = BPCPFHIS.DATA.AC;
        BPRFHIST.TX_CCY = BPCPFHIS.DATA.TX_CCY;
        BPRFHIST.DRCRFLG = BPCPFHIS.DATA.TX_DRCR_FLG;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '6';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '4';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
            B030_ADD_REC();
            if (pgmRtn) return;
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
                || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
                BPRFHIST.TX_AMT = BPRFHIST.TX_AMT - BPCPFHIS.DATA.TX_AMT;
            } else {
                BPRFHIST.TX_AMT = BPRFHIST.TX_AMT + BPCPFHIS.DATA.TX_AMT;
            }
            BPRFHIST.SUMUP_FLG = BPCPFHIS.DATA.SUMUP_FLG;
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            BPCUFHIS.DATA.FUNC = '2';
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
        }
    }
    public void B030_ADD_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-ADD-REC");
        IBS.init(SCCGWA, BPCUFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPCIFHIS.INPUT.REC_PT = BPRFHIST;
        BPCIFHIS.INPUT.REC_LEN = 690;
        if (BPCPFHIS.DATA.AC_DT > 0) {
            BPRFHIST.KEY.AC_DT = BPCPFHIS.DATA.AC_DT;
        } else {
            BPRFHIST.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCPFHIS.DATA.JRNNO > 0) {
            BPRFHIST.KEY.JRNNO = BPCPFHIS.DATA.JRNNO;
        } else {
            BPRFHIST.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        }
        GWA_BP_AREA.FHIS_CUR_SEQ = (short) (GWA_BP_AREA.FHIS_CUR_SEQ + 1);
        BPRFHIST.KEY.JRN_SEQ = GWA_BP_AREA.FHIS_CUR_SEQ;
        CEP.TRC(SCCGWA, GWA_BP_AREA.FHIS_CUR_SEQ);
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '1';
        BPRFHIST.KEY.AC = BPCPFHIS.DATA.AC;
        BPRFHIST.REF_NO = BPCPFHIS.DATA.REF_NO;
        BPRFHIST.TX_TOOL = BPCPFHIS.DATA.TX_TOOL;
        BPRFHIST.TX_STS = 'N';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPRFHIST.TX_STS = 'R';
        }
        CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
        BPRFHIST.TX_REV_DT = BPCPFHIS.DATA.TX_REV_DT;
        BPRFHIST.PRINT_FLG = 'Y';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
        } else {
            if (BPCPFHIS.DATA.PRINT_IND != ' ') {
                BPRFHIST.PRINT_FLG = BPCPFHIS.DATA.PRINT_IND;
            }
        }
        CEP.TRC(SCCGWA, BPRFHIST.PRINT_FLG);
        BPRFHIST.TX_VAL_DT = BPCPFHIS.DATA.TX_VAL_DT;
        BPRFHIST.TX_CCY = BPCPFHIS.DATA.TX_CCY;
        BPRFHIST.SUMUP_FLG = BPCPFHIS.DATA.SUMUP_FLG;
        WS_COMPUTE.WS_CPU1_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.CPY2CLS(SCCGWA, WS_COMPUTE.WS_CPU1_DATE+"", WS_COMPUTE.REDEFINES15);
        WS_COMPUTE.WS_CPU2_DATE = BPCPFHIS.DATA.TX_VAL_DT;
        IBS.CPY2CLS(SCCGWA, WS_COMPUTE.WS_CPU2_DATE+"", WS_COMPUTE.REDEFINES20);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            || SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            CEP.TRC(SCCGWA, WS_FHIST_DRCRFLG);
            if (WS_COMPUTE.REDEFINES15.WS_CPU1_YYYY == WS_COMPUTE.REDEFINES20.WS_CPU2_YYYY) {
                BPRFHIST.DRCRFLG = BPCPFHIS.DATA.TX_DRCR_FLG;
                BPRFHIST.TX_AMT = BPCPFHIS.DATA.TX_AMT * ( -1 );
            } else {
                if (WS_FHIST_DRCRFLG == 'D') {
                    BPRFHIST.DRCRFLG = 'C';
                }
                if (WS_FHIST_DRCRFLG == 'C') {
                    BPRFHIST.DRCRFLG = 'D';
                }
                BPRFHIST.TX_AMT = BPCPFHIS.DATA.TX_AMT;
            }
        } else {
            BPRFHIST.DRCRFLG = BPCPFHIS.DATA.TX_DRCR_FLG;
            BPRFHIST.TX_AMT = BPCPFHIS.DATA.TX_AMT;
            CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
        }
        BPRFHIST.CI_NO = BPCPFHIS.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPRFHIST.REMARK);
        BPRFHIST.REMARK = BPCPFHIS.DATA.REMARK;
        FHIST_REMARK_LEN = BPRFHIST.REMARK.length();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (BPCPFHIS.DATA.NARRATIVE.trim().length() == 0) {
                BPRFHIST.NARRATIVE = BPCPFHIS.DATA.NARRATIVE;
                FHIST_NARRATIVE_LEN = BPRFHIST.NARRATIVE.length();
                CEP.TRC(SCCGWA, BPRFHIST.NARRATIVE);
            } else {
                if (BPRFHIST.NARRATIVE == null) BPRFHIST.NARRATIVE = "";
                JIBS_tmp_int = BPRFHIST.NARRATIVE.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) BPRFHIST.NARRATIVE += " ";
                BPRFHIST.NARRATIVE = "REV " + BPRFHIST.NARRATIVE.substring(4);
                if (BPRFHIST.NARRATIVE == null) BPRFHIST.NARRATIVE = "";
                JIBS_tmp_int = BPRFHIST.NARRATIVE.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) BPRFHIST.NARRATIVE += " ";
                if (BPCPFHIS.DATA.NARRATIVE == null) BPCPFHIS.DATA.NARRATIVE = "";
                JIBS_tmp_int = BPCPFHIS.DATA.NARRATIVE.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) BPCPFHIS.DATA.NARRATIVE += " ";
                BPRFHIST.NARRATIVE = BPRFHIST.NARRATIVE.substring(0, 5 - 1) + BPCPFHIS.DATA.NARRATIVE + BPRFHIST.NARRATIVE.substring(5 + BPCPFHIS.DATA.NARRATIVE.length() - 1);
                CEP.TRC(SCCGWA, BPRFHIST.NARRATIVE);
            }
        } else {
            BPRFHIST.NARRATIVE = BPCPFHIS.DATA.NARRATIVE;
            FHIST_NARRATIVE_LEN = BPRFHIST.NARRATIVE.length();
            CEP.TRC(SCCGWA, BPRFHIST.NARRATIVE);
        }
        if (BPCPFHIS.DATA.AC_DT > 0) {
            BPRFHIST.KEY.AC_DT = BPCPFHIS.DATA.AC_DT;
        } else {
            BPRFHIST.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPCPFHIS.DATA.JRNNO > 0) {
            BPRFHIST.KEY.JRNNO = BPCPFHIS.DATA.JRNNO;
        } else {
            BPRFHIST.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        }
        if (BPCPFHIS.DATA.VCHNO.trim().length() == 0) {
            BPRFHIST.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        } else {
            BPRFHIST.VCHNO = BPCPFHIS.DATA.VCHNO;
        }
        BPRFHIST.APP_MMO = SCCGWA.COMM_AREA.AP_EXT_MMO;
        BPRFHIST.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (BPCPFHIS.DATA.TX_MMO.trim().length() > 0) {
            BPRFHIST.TX_MMO = BPCPFHIS.DATA.TX_MMO;
        } else {
            BPRFHIST.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
        }
        BPRFHIST.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRFHIST.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
        if (BPCPFHIS.DATA.PROD_CD.trim().length() > 0) {
            BPRFHIST.PROD_CD = BPCPFHIS.DATA.PROD_CD;
        } else {
            BPRFHIST.PROD_CD = SCCGWA.COMM_AREA.PROD_CODE;
        }
        BPRFHIST.PRDMO_CD = BPCPFHIS.DATA.PRDMO_CD;
        BPRFHIST.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRFHIST.TX_DP = SCCGWA.COMM_AREA.BR_DP.TR_DEP;
        BPRFHIST.TX_CHNL = SCCGWA.COMM_AREA.CHNL;
        BPRFHIST.TX_CHNL_JRN = GWA_SC_AREA.REQ_CHNL_JRN;
        BPRFHIST.TX_REQFM = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFHIST.TX_SYS_JRN = GWA_SC_AREA.REQ_SYS_JRN;
        BPRFHIST.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRFHIST.SUP1 = SCCGMSG.SUP1_ID;
        BPRFHIST.SUP2 = SCCGMSG.SUP2_ID;
        CEP.TRC(SCCGWA, SCCGMSG.SUP1_ID);
        CEP.TRC(SCCGWA, SCCGMSG.SUP2_ID);
        CEP.TRC(SCCGWA, BPRFHIST.SUP1);
        CEP.TRC(SCCGWA, BPRFHIST.SUP2);
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
    }
    public void B030_SEND_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-SEND-REC");
        IBS.init(SCCGWA, SCCWRTSQ);
        SCCWRTSQ.DATE = "" + BPRFHIST.KEY.AC_DT;
        JIBS_tmp_int = SCCWRTSQ.DATE.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) SCCWRTSQ.DATE = "0" + SCCWRTSQ.DATE;
        SCCWRTSQ.JRNNO = BPRFHIST.KEY.JRNNO;
        SCCWRTSQ.SEQ = BPRFHIST.KEY.JRN_SEQ;
        SCCWRTSQ.BK = BPRFHIST.RLT_BANK;
        SCCWRTSQ.BR = BPRFHIST.TX_BR;
        SCCWRTSQ.TIME = BPRFHIST.TX_TM;
        SCCWRTSQ.TX_CODE = BPRFHIST.TX_CD;
        SCCWRTSQ.MMO = BPRFHIST.TX_MMO;
        SCCWRTSQ.CH = BPRFHIST.TX_CHNL;
        SCCWRTSQ.AC = BPRFHIST.KEY.AC;
        SCCWRTSQ.CARD_NO = BPRFHIST.TX_TOOL;
        SCCWRTSQ.TX_CCY = BPRFHIST.TX_CCY;
        SCCWRTSQ.DR_CR_FLG = BPRFHIST.DRCRFLG;
        SCCWRTSQ.TX_AMT = BPRFHIST.TX_AMT;
        CEP.TRC(SCCGWA, SCCWRTSQ.DR_CR_FLG);
    }
    public void B030_SEND_REC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-SEND-REC-CN");
        SCCWRMSG.DATE = BPRFHIST.KEY.AC_DT;
        SCCWRMSG.JRNNO = "" + BPRFHIST.KEY.JRNNO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.SEQ = BPRFHIST.KEY.JRN_SEQ;
        if (BPRFHIST.DRCRFLG == 'C') {
            SCCWRMSG.AP_CODE = "22";
        } else {
            SCCWRMSG.AP_CODE = "21";
        }
        SCCWRMSG.CI_NO = BPRFHIST.CI_NO;
        WS_ACDATE = BPRFHIST.KEY.AC_DT;
        JIBS_tmp_str[0] = "" + WS_ACDATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + WS_ACDATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_ACDATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        SCCWRMSG.MMO = BPRFHIST.TX_MMO;
        SCCWRMSG.CCY = BPRFHIST.TX_CCY;
        SCCWRMSG.AMT = BPRFHIST.TX_AMT;
        SCCWRMSG.AC = BPRFHIST.KEY.AC;
        WS_CNT = 32;
        if (BPRFHIST.KEY.AC == null) BPRFHIST.KEY.AC = "";
        JIBS_tmp_int = BPRFHIST.KEY.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPRFHIST.KEY.AC += " ";
        while (WS_CNT >= 5 
            && BPRFHIST.KEY.AC.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() <= 0) {
            WS_CNT = (short) (WS_CNT - 1);
        }
        CEP.TRC(SCCGWA, WS_CNT);
        WS_CNT = (short) (WS_CNT - 3);
        if (BPRFHIST.KEY.AC == null) BPRFHIST.KEY.AC = "";
        JIBS_tmp_int = BPRFHIST.KEY.AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) BPRFHIST.KEY.AC += " ";
        SCCWRMSG.ACL4 = BPRFHIST.KEY.AC.substring(WS_CNT - 1, WS_CNT + 4 - 1);
    }
    public void B030_MOD_ORI_TXN_REC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B030-MOD-ORI-TXN-REC");
        IBS.init(SCCGWA, BPCUFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        WS_HISTORY_FLAG = ' ';
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_STM_IND);
        BPRFHIST.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRFHIST.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '3';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '4';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPFHIS.RC);
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "ABC0000000000000000000000");
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
        CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
        CEP.TRC(SCCGWA, BPRFHIST.TX_CCY);
        CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC);
        CEP.TRC(SCCGWA, BPRFHIST.REF_NO);
        CEP.TRC(SCCGWA, WS_HISTORY_FLAG);
        WS_STATUS_FLG = 'C';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE) 
            && WS_HISTORY_FLAG != 'Y') {
            WS_HISTORY_FLAG = 'N';
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
            CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
            if (SCCGWA.COMM_AREA.REVERSAL_IND != 'Y') {
                if (BPRFHIST.DRCRFLG == BPCPFHIS.DATA.TX_DRCR_FLG 
                    && BPRFHIST.TX_CCY.equalsIgnoreCase(BPCPFHIS.DATA.TX_CCY) 
                    && BPRFHIST.TX_AMT == BPCPFHIS.DATA.TX_AMT 
                    && BPRFHIST.KEY.AC.equalsIgnoreCase(BPCPFHIS.DATA.AC) 
                    && BPRFHIST.REF_NO.equalsIgnoreCase(BPCPFHIS.DATA.REF_NO) 
                    && BPRFHIST.TX_STS == 'N') {
                    CEP.TRC(SCCGWA, "NOT FEE");
                    CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
                    CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
                    CEP.TRC(SCCGWA, BPRFHIST.TX_CCY);
                    CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
                    CEP.TRC(SCCGWA, BPRFHIST.KEY.AC);
                    CEP.TRC(SCCGWA, BPRFHIST.RLT_AC);
                    CEP.TRC(SCCGWA, BPRFHIST.REF_NO);
                    CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
                    if (BPCPFHIS.DATA.RLT_AC.trim().length() > 0) {
                        if (BPRFHIST.RLT_AC.equalsIgnoreCase(BPCPFHIS.DATA.RLT_AC)) {
                            WS_HISTORY_FLAG = 'Y';
                            WS_STATUS_FLG = 'N';
                        }
                    } else {
                        WS_HISTORY_FLAG = 'Y';
                        WS_STATUS_FLG = 'N';
                    }
                }
                CEP.TRC(SCCGWA, WS_STATUS_FLG);
                CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
                CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
                CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY);
                CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
                CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
                CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
                CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
                CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
                CEP.TRC(SCCGWA, BPRFHIST.TX_CCY);
                CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
                CEP.TRC(SCCGWA, BPRFHIST.KEY.AC);
                CEP.TRC(SCCGWA, BPRFHIST.RLT_AC);
                CEP.TRC(SCCGWA, BPRFHIST.REF_NO);
                CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
            } else {
                if (BPRFHIST.DRCRFLG == BPCPFHIS.DATA.TX_DRCR_FLG 
                    && BPRFHIST.TX_CCY.equalsIgnoreCase(BPCPFHIS.DATA.TX_CCY) 
                    && BPRFHIST.TX_AMT == BPCPFHIS.DATA.TX_AMT 
                    && BPRFHIST.KEY.AC.equalsIgnoreCase(BPCPFHIS.DATA.AC) 
                    && BPRFHIST.RLT_AC.equalsIgnoreCase(BPCPFHIS.DATA.RLT_AC) 
                    && BPRFHIST.REF_NO.equalsIgnoreCase(BPCPFHIS.DATA.REF_NO)) {
                    CEP.TRC(SCCGWA, "THAT FEE");
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.AC);
                    CEP.TRC(SCCGWA, BPRFHIST.RLT_AC);
                    CEP.TRC(SCCGWA, BPCPFHIS.DATA.REF_NO);
                    CEP.TRC(SCCGWA, BPRFHIST.DRCRFLG);
                    CEP.TRC(SCCGWA, BPRFHIST.TX_CCY);
                    CEP.TRC(SCCGWA, BPRFHIST.TX_AMT);
                    CEP.TRC(SCCGWA, BPRFHIST.KEY.AC);
                    CEP.TRC(SCCGWA, BPRFHIST.REF_NO);
                    CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
                    WS_HISTORY_FLAG = 'Y';
                    WS_STATUS_FLG = 'N';
                }
            }
            if (WS_HISTORY_FLAG == 'Y') {
                if (BPRFHIST.TX_STS == 'C') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ALREADY_CANCEL, BPCPFHIS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                BPRFHIST.TX_STS = 'C';
                BPRFHIST.TX_REV_DT = BPCPFHIS.DATA.TX_REV_DT;
                BPCUFHIS.DATA.POINTER = BPRFHIST;
                BPCUFHIS.DATA.REC_LEN = 690;
                BPCUFHIS.DATA.FUNC = '2';
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCUFHIS.RC);
                WS_FHIST_DRCRFLG = BPRFHIST.DRCRFLG;
                CEP.TRC(SCCGWA, WS_FHIST_DRCRFLG);
                if (BPRFHIST.KEY.AC.trim().length() > 0) {
                    CEP.TRC(SCCGWA, BPRFHIST.TX_STS);
                }
                CEP.TRC(SCCGWA, WS_FHIST_DRCRFLG);
            }
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            BPCUFHIS.DATA.FUNC = '4';
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
        }
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '5';
        CEP.TRC(SCCGWA, WS_STATUS_FLG);
        if (WS_STATUS_FLG == 'C') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCPFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_UPD_FHIST, BPCUFHIS);
        CEP.TRC(SCCGWA, BPCUFHIS.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_RD = new DBParm();
        BPTTLT_RD.TableName = "BPTTLT";
        IBS.READ(SCCGWA, BPRTLT, BPTTLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TLT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TLT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_FHIS, BPCIFHIS);
        CEP.TRC(SCCGWA, BPCIFHIS.OUTPUT.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPFHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCOWRTSQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCOWRTTSQ-WRTQ", SCCWRTSQ);
    }
    public void S000_CALL_CIZACCU_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void S000_CALL_SCZWRMSG_CN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG);
    }
    public void S00_GET_TIME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTIME);
        SCZTIME SCZTIME = new SCZTIME();
        SCZTIME.MP(SCCGWA, SCCTIME);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_INFO);
    }
    public void R000_GET_PART_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.ACAC_NO = WS_H_ACO_AC;
        CICQACAC.FUNC = 'A';
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        WS_AC_HASH = IBS.CalcHash(99991, WS_H_ACO_AC);
        if (WS_AC_HASH >= 0 
                && WS_AC_HASH <= 783) {
            WS_PART_NO = 1;
        } else if (WS_AC_HASH >= 784 
                && WS_AC_HASH <= 1567) {
            WS_PART_NO = 2;
        } else if (WS_AC_HASH >= 1568 
                && WS_AC_HASH <= 2351) {
            WS_PART_NO = 3;
        } else if (WS_AC_HASH >= 2352 
                && WS_AC_HASH <= 3135) {
            WS_PART_NO = 4;
        } else if (WS_AC_HASH >= 3136 
                && WS_AC_HASH <= 3919) {
            WS_PART_NO = 5;
        } else if (WS_AC_HASH >= 3920 
                && WS_AC_HASH <= 4703) {
            WS_PART_NO = 6;
        } else if (WS_AC_HASH >= 4704 
                && WS_AC_HASH <= 5487) {
            WS_PART_NO = 7;
        } else if (WS_AC_HASH >= 5488 
                && WS_AC_HASH <= 6271) {
            WS_PART_NO = 8;
        } else if (WS_AC_HASH >= 6272 
                && WS_AC_HASH <= 7055) {
            WS_PART_NO = 9;
        } else if (WS_AC_HASH >= 7056 
                && WS_AC_HASH <= 7839) {
            WS_PART_NO = 10;
        } else if (WS_AC_HASH >= 7840 
                && WS_AC_HASH <= 8623) {
            WS_PART_NO = 11;
        } else if (WS_AC_HASH >= 8624 
                && WS_AC_HASH <= 9407) {
            WS_PART_NO = 12;
        } else if (WS_AC_HASH >= 9408 
                && WS_AC_HASH <= 10191) {
            WS_PART_NO = 13;
        } else if (WS_AC_HASH >= 10192 
                && WS_AC_HASH <= 10975) {
            WS_PART_NO = 14;
        } else if (WS_AC_HASH >= 10976 
                && WS_AC_HASH <= 11759) {
            WS_PART_NO = 15;
        } else if (WS_AC_HASH >= 11760 
                && WS_AC_HASH <= 12543) {
            WS_PART_NO = 16;
        } else if (WS_AC_HASH >= 12544 
                && WS_AC_HASH <= 13327) {
            WS_PART_NO = 17;
        } else if (WS_AC_HASH >= 13328 
                && WS_AC_HASH <= 14111) {
            WS_PART_NO = 18;
        } else if (WS_AC_HASH >= 14112 
                && WS_AC_HASH <= 14895) {
            WS_PART_NO = 19;
        } else if (WS_AC_HASH >= 14896 
                && WS_AC_HASH <= 15679) {
            WS_PART_NO = 20;
        } else if (WS_AC_HASH >= 15680 
                && WS_AC_HASH <= 16463) {
            WS_PART_NO = 21;
        } else if (WS_AC_HASH >= 16464 
                && WS_AC_HASH <= 17247) {
            WS_PART_NO = 22;
        } else if (WS_AC_HASH >= 17248 
                && WS_AC_HASH <= 18031) {
            WS_PART_NO = 23;
        } else if (WS_AC_HASH >= 18032 
                && WS_AC_HASH <= 18815) {
            WS_PART_NO = 24;
        } else if (WS_AC_HASH >= 18816 
                && WS_AC_HASH <= 19599) {
            WS_PART_NO = 25;
        } else if (WS_AC_HASH >= 19600 
                && WS_AC_HASH <= 20383) {
            WS_PART_NO = 26;
        } else if (WS_AC_HASH >= 20384 
                && WS_AC_HASH <= 21167) {
            WS_PART_NO = 27;
        } else if (WS_AC_HASH >= 21168 
                && WS_AC_HASH <= 21951) {
            WS_PART_NO = 28;
        } else if (WS_AC_HASH >= 21952 
                && WS_AC_HASH <= 22735) {
            WS_PART_NO = 29;
        } else if (WS_AC_HASH >= 22736 
                && WS_AC_HASH <= 23519) {
            WS_PART_NO = 30;
        } else if (WS_AC_HASH >= 23520 
                && WS_AC_HASH <= 24303) {
            WS_PART_NO = 31;
        } else if (WS_AC_HASH >= 24304 
                && WS_AC_HASH <= 25087) {
            WS_PART_NO = 32;
        } else if (WS_AC_HASH >= 25088 
                && WS_AC_HASH <= 25871) {
            WS_PART_NO = 33;
        } else if (WS_AC_HASH >= 25872 
                && WS_AC_HASH <= 26655) {
            WS_PART_NO = 34;
        } else if (WS_AC_HASH >= 26656 
                && WS_AC_HASH <= 27439) {
            WS_PART_NO = 35;
        } else if (WS_AC_HASH >= 27440 
                && WS_AC_HASH <= 28223) {
            WS_PART_NO = 36;
        } else if (WS_AC_HASH >= 28224 
                && WS_AC_HASH <= 29007) {
            WS_PART_NO = 37;
        } else if (WS_AC_HASH >= 29008 
                && WS_AC_HASH <= 29790) {
            WS_PART_NO = 38;
        } else if (WS_AC_HASH >= 29791 
                && WS_AC_HASH <= 30570) {
            WS_PART_NO = 39;
        } else if (WS_AC_HASH >= 30571 
                && WS_AC_HASH <= 31350) {
            WS_PART_NO = 40;
        } else if (WS_AC_HASH >= 31351 
                && WS_AC_HASH <= 32130) {
            WS_PART_NO = 41;
        } else if (WS_AC_HASH >= 32131 
                && WS_AC_HASH <= 32910) {
            WS_PART_NO = 42;
        } else if (WS_AC_HASH >= 32911 
                && WS_AC_HASH <= 33690) {
            WS_PART_NO = 43;
        } else if (WS_AC_HASH >= 33691 
                && WS_AC_HASH <= 34470) {
            WS_PART_NO = 44;
        } else if (WS_AC_HASH >= 34471 
                && WS_AC_HASH <= 35250) {
            WS_PART_NO = 45;
        } else if (WS_AC_HASH >= 35251 
                && WS_AC_HASH <= 36030) {
            WS_PART_NO = 46;
        } else if (WS_AC_HASH >= 36031 
                && WS_AC_HASH <= 36810) {
            WS_PART_NO = 47;
        } else if (WS_AC_HASH >= 36811 
                && WS_AC_HASH <= 37590) {
            WS_PART_NO = 48;
        } else if (WS_AC_HASH >= 37591 
                && WS_AC_HASH <= 38370) {
            WS_PART_NO = 49;
        } else if (WS_AC_HASH >= 38371 
                && WS_AC_HASH <= 39150) {
            WS_PART_NO = 50;
        } else if (WS_AC_HASH >= 39151 
                && WS_AC_HASH <= 39930) {
            WS_PART_NO = 51;
        } else if (WS_AC_HASH >= 39931 
                && WS_AC_HASH <= 40710) {
            WS_PART_NO = 52;
        } else if (WS_AC_HASH >= 40711 
                && WS_AC_HASH <= 41490) {
            WS_PART_NO = 53;
        } else if (WS_AC_HASH >= 41491 
                && WS_AC_HASH <= 42270) {
            WS_PART_NO = 54;
        } else if (WS_AC_HASH >= 42271 
                && WS_AC_HASH <= 43050) {
            WS_PART_NO = 55;
        } else if (WS_AC_HASH >= 43051 
                && WS_AC_HASH <= 43830) {
            WS_PART_NO = 56;
        } else if (WS_AC_HASH >= 43831 
                && WS_AC_HASH <= 44610) {
            WS_PART_NO = 57;
        } else if (WS_AC_HASH >= 44611 
                && WS_AC_HASH <= 45390) {
            WS_PART_NO = 58;
        } else if (WS_AC_HASH >= 45391 
                && WS_AC_HASH <= 46170) {
            WS_PART_NO = 59;
        } else if (WS_AC_HASH >= 46171 
                && WS_AC_HASH <= 46950) {
            WS_PART_NO = 60;
        } else if (WS_AC_HASH >= 46951 
                && WS_AC_HASH <= 47730) {
            WS_PART_NO = 61;
        } else if (WS_AC_HASH >= 47731 
                && WS_AC_HASH <= 48510) {
            WS_PART_NO = 62;
        } else if (WS_AC_HASH >= 48511 
                && WS_AC_HASH <= 49290) {
            WS_PART_NO = 63;
        } else if (WS_AC_HASH >= 49291 
                && WS_AC_HASH <= 50070) {
            WS_PART_NO = 64;
        } else if (WS_AC_HASH >= 50071 
                && WS_AC_HASH <= 50850) {
            WS_PART_NO = 65;
        } else if (WS_AC_HASH >= 50851 
                && WS_AC_HASH <= 51630) {
            WS_PART_NO = 66;
        } else if (WS_AC_HASH >= 51631 
                && WS_AC_HASH <= 52410) {
            WS_PART_NO = 67;
        } else if (WS_AC_HASH >= 52411 
                && WS_AC_HASH <= 53190) {
            WS_PART_NO = 68;
        } else if (WS_AC_HASH >= 53191 
                && WS_AC_HASH <= 53970) {
            WS_PART_NO = 69;
        } else if (WS_AC_HASH >= 53971 
                && WS_AC_HASH <= 54750) {
            WS_PART_NO = 70;
        } else if (WS_AC_HASH >= 54751 
                && WS_AC_HASH <= 55530) {
            WS_PART_NO = 71;
        } else if (WS_AC_HASH >= 55531 
                && WS_AC_HASH <= 56310) {
            WS_PART_NO = 72;
        } else if (WS_AC_HASH >= 56311 
                && WS_AC_HASH <= 57090) {
            WS_PART_NO = 73;
        } else if (WS_AC_HASH >= 57091 
                && WS_AC_HASH <= 57870) {
            WS_PART_NO = 74;
        } else if (WS_AC_HASH >= 57871 
                && WS_AC_HASH <= 58650) {
            WS_PART_NO = 75;
        } else if (WS_AC_HASH >= 58651 
                && WS_AC_HASH <= 59430) {
            WS_PART_NO = 76;
        } else if (WS_AC_HASH >= 59431 
                && WS_AC_HASH <= 60210) {
            WS_PART_NO = 77;
        } else if (WS_AC_HASH >= 60211 
                && WS_AC_HASH <= 60990) {
            WS_PART_NO = 78;
        } else if (WS_AC_HASH >= 60991 
                && WS_AC_HASH <= 61770) {
            WS_PART_NO = 79;
        } else if (WS_AC_HASH >= 61771 
                && WS_AC_HASH <= 62550) {
            WS_PART_NO = 80;
        } else if (WS_AC_HASH >= 62551 
                && WS_AC_HASH <= 63330) {
            WS_PART_NO = 81;
        } else if (WS_AC_HASH >= 63331 
                && WS_AC_HASH <= 64110) {
            WS_PART_NO = 82;
        } else if (WS_AC_HASH >= 64111 
                && WS_AC_HASH <= 64890) {
            WS_PART_NO = 83;
        } else if (WS_AC_HASH >= 64891 
                && WS_AC_HASH <= 65670) {
            WS_PART_NO = 84;
        } else if (WS_AC_HASH >= 65671 
                && WS_AC_HASH <= 66450) {
            WS_PART_NO = 85;
        } else if (WS_AC_HASH >= 66451 
                && WS_AC_HASH <= 67230) {
            WS_PART_NO = 86;
        } else if (WS_AC_HASH >= 67231 
                && WS_AC_HASH <= 68010) {
            WS_PART_NO = 87;
        } else if (WS_AC_HASH >= 68011 
                && WS_AC_HASH <= 68790) {
            WS_PART_NO = 88;
        } else if (WS_AC_HASH >= 68791 
                && WS_AC_HASH <= 69570) {
            WS_PART_NO = 89;
        } else if (WS_AC_HASH >= 69571 
                && WS_AC_HASH <= 70350) {
            WS_PART_NO = 90;
        } else if (WS_AC_HASH >= 70351 
                && WS_AC_HASH <= 71130) {
            WS_PART_NO = 91;
        } else if (WS_AC_HASH >= 71131 
                && WS_AC_HASH <= 71910) {
            WS_PART_NO = 92;
        } else if (WS_AC_HASH >= 71911 
                && WS_AC_HASH <= 72690) {
            WS_PART_NO = 93;
        } else if (WS_AC_HASH >= 72691 
                && WS_AC_HASH <= 73470) {
            WS_PART_NO = 94;
        } else if (WS_AC_HASH >= 73471 
                && WS_AC_HASH <= 74250) {
            WS_PART_NO = 95;
        } else if (WS_AC_HASH >= 74251 
                && WS_AC_HASH <= 75030) {
            WS_PART_NO = 96;
        } else if (WS_AC_HASH >= 75031 
                && WS_AC_HASH <= 75810) {
            WS_PART_NO = 97;
        } else if (WS_AC_HASH >= 75811 
                && WS_AC_HASH <= 76590) {
            WS_PART_NO = 98;
        } else if (WS_AC_HASH >= 76591 
                && WS_AC_HASH <= 77370) {
            WS_PART_NO = 99;
        } else if (WS_AC_HASH >= 77371 
                && WS_AC_HASH <= 78150) {
            WS_PART_NO = 100;
        } else if (WS_AC_HASH >= 78151 
                && WS_AC_HASH <= 78930) {
            WS_PART_NO = 101;
        } else if (WS_AC_HASH >= 78931 
                && WS_AC_HASH <= 79710) {
            WS_PART_NO = 102;
        } else if (WS_AC_HASH >= 79711 
                && WS_AC_HASH <= 80490) {
            WS_PART_NO = 103;
        } else if (WS_AC_HASH >= 80491 
                && WS_AC_HASH <= 81270) {
            WS_PART_NO = 104;
        } else if (WS_AC_HASH >= 81271 
                && WS_AC_HASH <= 82050) {
            WS_PART_NO = 105;
        } else if (WS_AC_HASH >= 82051 
                && WS_AC_HASH <= 82830) {
            WS_PART_NO = 106;
        } else if (WS_AC_HASH >= 82831 
                && WS_AC_HASH <= 83610) {
            WS_PART_NO = 107;
        } else if (WS_AC_HASH >= 83611 
                && WS_AC_HASH <= 84390) {
            WS_PART_NO = 108;
        } else if (WS_AC_HASH >= 84391 
                && WS_AC_HASH <= 85170) {
            WS_PART_NO = 109;
        } else if (WS_AC_HASH >= 85171 
                && WS_AC_HASH <= 85950) {
            WS_PART_NO = 110;
        } else if (WS_AC_HASH >= 85951 
                && WS_AC_HASH <= 86730) {
            WS_PART_NO = 111;
        } else if (WS_AC_HASH >= 86731 
                && WS_AC_HASH <= 87510) {
            WS_PART_NO = 112;
        } else if (WS_AC_HASH >= 87511 
                && WS_AC_HASH <= 88290) {
            WS_PART_NO = 113;
        } else if (WS_AC_HASH >= 88291 
                && WS_AC_HASH <= 89070) {
            WS_PART_NO = 114;
        } else if (WS_AC_HASH >= 89071 
                && WS_AC_HASH <= 89850) {
            WS_PART_NO = 115;
        } else if (WS_AC_HASH >= 89851 
                && WS_AC_HASH <= 90630) {
            WS_PART_NO = 116;
        } else if (WS_AC_HASH >= 90631 
                && WS_AC_HASH <= 91410) {
            WS_PART_NO = 117;
        } else if (WS_AC_HASH >= 91411 
                && WS_AC_HASH <= 92190) {
            WS_PART_NO = 118;
        } else if (WS_AC_HASH >= 92191 
                && WS_AC_HASH <= 92970) {
            WS_PART_NO = 119;
        } else if (WS_AC_HASH >= 92971 
                && WS_AC_HASH <= 93750) {
            WS_PART_NO = 120;
        } else if (WS_AC_HASH >= 93751 
                && WS_AC_HASH <= 94530) {
            WS_PART_NO = 121;
        } else if (WS_AC_HASH >= 94531 
                && WS_AC_HASH <= 95310) {
            WS_PART_NO = 122;
        } else if (WS_AC_HASH >= 95311 
                && WS_AC_HASH <= 96090) {
            WS_PART_NO = 123;
        } else if (WS_AC_HASH >= 96091 
                && WS_AC_HASH <= 96870) {
            WS_PART_NO = 124;
        } else if (WS_AC_HASH >= 96871 
                && WS_AC_HASH <= 97650) {
            WS_PART_NO = 125;
        } else if (WS_AC_HASH >= 97651 
                && WS_AC_HASH <= 98430) {
            WS_PART_NO = 126;
        } else if (WS_AC_HASH >= 98431 
                && WS_AC_HASH <= 99210) {
            WS_PART_NO = 127;
        } else if (WS_AC_HASH >= 99211 
                && WS_AC_HASH <= 99990) {
            WS_PART_NO = 128;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            WS_PART_NO = WS_PART_NO;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            WS_PART_NO = WS_PART_NO + 128;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("IB")) {
            WS_PART_NO = 265;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("EQ")) {
            WS_PART_NO = 275;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("FX")) {
            WS_PART_NO = 270;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("BP")) {
            WS_PART_NO = 260;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("PN")) {
            WS_PART_NO = 280;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("LN")) {
            WS_PART_NO = 285;
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("PL")) {
            WS_PART_NO = 290;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INEFC_PRDT_CD);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPFHIS.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPFHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPFHIS = ");
            CEP.TRC(SCCGWA, BPCPFHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
