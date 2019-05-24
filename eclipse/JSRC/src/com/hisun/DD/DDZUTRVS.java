package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPIAEV;
import com.hisun.AI.AICPQMIB;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCEX;
import com.hisun.BP.BPCFAMTA;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCOCLWD;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCTCALF;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQACRL;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZUTRVS {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD220";
    String K_LOCAL_CCY = "156";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    String WS_BUY_CCY = " ";
    char WS_BUY_CCY_TYPE = ' ';
    double WS_BUY_AMT = 0;
    String WS_SELL_CCY = " ";
    char WS_SELL_CCY_TYPE = ' ';
    double WS_SELL_AMT = 0;
    int WS_TMP_DT = 0;
    String WS_FR_CI_NO = " ";
    String WS_TO_CI_NO = " ";
    String WS_SAVE_STRAC_FR_AC = " ";
    String WS_SAVE_STRAC_TO_AC = " ";
    char WS_FR_CI_TYP = ' ';
    char WS_TO_CI_TYP = ' ';
    char WS_FR_AC_TYPE = ' ';
    String WS_FR_AC = " ";
    String WS_TO_AC = " ";
    char WS_ENTY_TYPE = ' ';
    char WS_DR_ENTY_TYP = ' ';
    char WS_CR_ENTY_TYP = ' ';
    String WS_VS_AC = " ";
    double WS_AVL_BAL = 0;
    double WS_CURR_BAL = 0;
    double WS_SPAMT = 0;
    double WS_DR_BAL1 = 0;
    double WS_CR_BAL1 = 0;
    double WS_MARGIN_BAL = 0;
    String WS_REL_AC_NO = " ";
    double WS_DR_CURR_BAL = 0;
    double WS_CR_CURR_BAL = 0;
    char WS_REC_FLG = ' ';
    char WS_SAME_NAME_FLG = ' ';
    char WS_AC_ROUTINE_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDCOTRVS DDCOTRVS = new DDCOTRVS();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    BPCEX BPCEX = new BPCEX();
    AICPIAEV AICPIAEV = new AICPIAEV();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    CICACCU CICACCU = new CICACCU();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICUUPIA AICUUPIA = new AICUUPIA();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    DDCRVSTR DDCRVSTR = new DDCRVSTR();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDRFHIS DDRFHIS = new DDRFHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRVSABI DDRVSABI = new DDRVSABI();
    DDRVSTRN DDRVSTRN = new DDRVSTRN();
    SCCGWA SCCGWA;
    DDCUTRVS DDCUTRVS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCUTRVS DDCUTRVS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUTRVS = DDCUTRVS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUTRVS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_REMARK);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_REMARK);
        B010_CHK_INPUT_DATA();
        if (pgmRtn) return;
        B020_AC_RELAT_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B030_CHECK_DRAW_PROC();
            if (pgmRtn) return;
            B060_OUTPUT_PROCESS();
            if (pgmRtn) return;
        } else {
            B040_CANCLE_DRAW_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUTRVS.VS_DRAC);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_DRNM);
        CEP.TRC(SCCGWA, DDCUTRVS.TXCCY);
        CEP.TRC(SCCGWA, DDCUTRVS.CCY_TYP);
        CEP.TRC(SCCGWA, DDCUTRVS.BAL);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_OPT);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_SPFLG);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_SPAMT);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_AMT);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_STLT);
        CEP.TRC(SCCGWA, DDCUTRVS.OLD_CRAC);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_CRAC);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_CRNM);
        CEP.TRC(SCCGWA, DDCUTRVS.MMO);
        if (DDCUTRVS.VS_DRAC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCUTRVS.VS_CRAC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCUTRVS.TXCCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCUTRVS.VS_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TRF_AMT_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        } else {
            if (DDCUTRVS.VS_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
        if (DDCUTRVS.VS_STLT == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STL_MTH_M_INPT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DDCUTRVS.VS_OPT != ' ') {
            if (DDCUTRVS.VS_SPAMT == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            } else {
                if (DDCUTRVS.VS_SPAMT != DDCUTRVS.VS_AMT) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_M_EQUAL);
                }
            }
        }
        if (DDCUTRVS.VS_SPFLG == '2' 
            && DDCUTRVS.OLD_CRAC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OLD_CRAC_M_INPUT);
        }
    }
    public void B020_AC_RELAT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCUTRVS.VS_DRAC;
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_ENTY_TYP);
        if (CICQACRI.O_DATA.O_ENTY_TYP != '4' 
            && CICQACRI.O_DATA.O_ENTY_TYP != '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_DR_ENTY_TYP = CICQACRI.O_DATA.O_ENTY_TYP;
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = DDCUTRVS.VS_CRAC;
        CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_ENTY_TYP);
        if (CICQACRI.O_DATA.O_ENTY_TYP != '4' 
            && CICQACRI.O_DATA.O_ENTY_TYP != '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYP_ERR;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_CR_ENTY_TYP = CICQACRI.O_DATA.O_ENTY_TYP;
        if (WS_DR_ENTY_TYP == '4') {
            if (WS_CR_ENTY_TYP == '4') {
                B021_AC_RELAT_CHECK();
                if (pgmRtn) return;
                B022_AC_RELAT_CHECK();
                if (pgmRtn) return;
                if (!WS_REL_AC_NO.equalsIgnoreCase(CICQACRL.O_DATA.O_REL_AC_NO)) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_SAME_AC);
                }
            } else {
                B021_AC_RELAT_CHECK();
                if (pgmRtn) return;
                if (!WS_REL_AC_NO.equalsIgnoreCase(DDCUTRVS.VS_CRAC)) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_SAME_AC);
                }
            }
        } else {
            if (WS_CR_ENTY_TYP == '4') {
                B022_AC_RELAT_CHECK();
                if (pgmRtn) return;
                if (!DDCUTRVS.VS_DRAC.equalsIgnoreCase(CICQACRL.O_DATA.O_REL_AC_NO)) {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_SAME_AC);
                }
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_SAME_AC);
            }
        }
    }
    public void B021_AC_RELAT_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = DDCUTRVS.VS_DRAC;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        WS_REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
    }
    public void B022_AC_RELAT_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = DDCUTRVS.VS_CRAC;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
    }
    public void B030_CHECK_DRAW_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUTRVS.OLD_CRAC);
        if (DDCUTRVS.VS_SPFLG == '2' 
            && (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC))) {
        } else {
            B031_CHECK_DRBAL_PROC();
            if (pgmRtn) return;
        }
        B031_VSTRN_DRBAL_PROC();
        if (pgmRtn) return;
        if (WS_DR_ENTY_TYP == '4') {
            if (DDCUTRVS.VS_SPFLG == '2' 
                && (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC))) {
                B060_BP_NFHIS_PROC_DR();
                if (pgmRtn) return;
            } else {
                B050_FIN_TX_HIS_PROC_DR();
                if (pgmRtn) return;
            }
        }
        if (DDCUTRVS.VS_SPFLG == '2' 
            && (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC))) {
        } else {
            B031_CHECK_CRBAL_PROC();
            if (pgmRtn) return;
        }
        B031_VSTRN_CRBAL_PROC();
        if (pgmRtn) return;
        if (WS_CR_ENTY_TYP == '4') {
            if (DDCUTRVS.VS_SPFLG == '2' 
                && (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC))) {
                B060_BP_NFHIS_PROC_CR();
                if (pgmRtn) return;
            } else {
                B050_FIN_TX_HIS_PROC_CR();
                if (pgmRtn) return;
            }
        }
    }
    public void B031_CHECK_DRBAL_PROC() throws IOException,SQLException,Exception {
        WS_VS_AC = DDCUTRVS.VS_DRAC;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        WS_AVL_BAL = DDRCCY.CURR_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
        if (DDCUTRVS.VS_AMT > WS_AVL_BAL) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DRW_AMT_OVER_LMT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (WS_DR_ENTY_TYP == '4') {
            WS_DR_CURR_BAL = DDRCCY.CURR_BAL - DDCUTRVS.VS_AMT;
            DDRCCY.CURR_BAL = WS_DR_CURR_BAL;
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        } else {
            WS_MARGIN_BAL = DDRCCY.MARGIN_BAL + DDCUTRVS.VS_AMT;
            WS_DR_CURR_BAL = DDRCCY.CURR_BAL;
            DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
        WS_DR_BAL1 = WS_DR_CURR_BAL;
        CEP.TRC(SCCGWA, WS_DR_CURR_BAL);
    }
    public void B031_VSTRN_DRBAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUTRVS.VS_OPT);
        if (DDCUTRVS.VS_OPT != ' ') {
            IBS.init(SCCGWA, DDRVSTRN);
            DDRVSTRN.KEY.VS_AC = DDCUTRVS.VS_DRAC;
            DDRVSTRN.KEY.SPECAL_TYP = "" + DDCUTRVS.VS_OPT;
            JIBS_tmp_int = DDRVSTRN.KEY.SPECAL_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) DDRVSTRN.KEY.SPECAL_TYP = "0" + DDRVSTRN.KEY.SPECAL_TYP;
            DDRVSTRN.KEY.DR_CR_FLG = "D";
            if (DDCUTRVS.VS_SPFLG == '1') {
                B036_SPFLG_1_DR_PROC();
                if (pgmRtn) return;
            } else if (DDCUTRVS.VS_SPFLG == '2') {
                B036_SPFLG_2_DR_PROC();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SPAMT_INVAL);
            }
        }
    }
    public void B031_CHECK_CRBAL_PROC() throws IOException,SQLException,Exception {
        WS_VS_AC = DDCUTRVS.VS_CRAC;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        if (WS_CR_ENTY_TYP == '4') {
            WS_CR_CURR_BAL = DDRCCY.CURR_BAL + DDCUTRVS.VS_AMT;
            DDRCCY.CURR_BAL = WS_CR_CURR_BAL;
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        } else {
            WS_MARGIN_BAL = DDRCCY.MARGIN_BAL - DDCUTRVS.VS_AMT;
            WS_CR_CURR_BAL = DDRCCY.CURR_BAL;
            DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        }
        WS_CR_BAL1 = WS_CR_CURR_BAL;
        CEP.TRC(SCCGWA, WS_CR_CURR_BAL);
    }
    public void B031_VSTRN_CRBAL_PROC() throws IOException,SQLException,Exception {
        if (DDCUTRVS.VS_OPT != ' ') {
            IBS.init(SCCGWA, DDRVSTRN);
            DDRVSTRN.KEY.VS_AC = DDCUTRVS.VS_CRAC;
            DDRVSTRN.KEY.SPECAL_TYP = "" + DDCUTRVS.VS_OPT;
            JIBS_tmp_int = DDRVSTRN.KEY.SPECAL_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) DDRVSTRN.KEY.SPECAL_TYP = "0" + DDRVSTRN.KEY.SPECAL_TYP;
            DDRVSTRN.KEY.DR_CR_FLG = "C";
            if (DDCUTRVS.VS_SPFLG == '1') {
                B036_SPFLG_1_CR_PROC();
                if (pgmRtn) return;
            } else if (DDCUTRVS.VS_SPFLG == '2') {
                B036_SPFLG_2_CR_PROC();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SPAMT_INVAL);
            }
        }
    }
    public void B035_VSAC_DR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUTRVS.VS_DRAC);
        CEP.TRC(SCCGWA, DDCUTRVS.TXCCY);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_OPT);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_SPAMT);
        IBS.init(SCCGWA, DDRVSTRN);
        DDRVSTRN.KEY.VS_AC = DDCUTRVS.VS_DRAC;
        DDRVSTRN.KEY.SPECAL_TYP = "" + DDCUTRVS.VS_OPT;
        JIBS_tmp_int = DDRVSTRN.KEY.SPECAL_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DDRVSTRN.KEY.SPECAL_TYP = "0" + DDRVSTRN.KEY.SPECAL_TYP;
        DDRVSTRN.CCY = DDCUTRVS.TXCCY;
        DDRVSTRN.CCY_TYP = DDCUTRVS.CCY_TYP;
        DDRVSTRN.KEY.DR_CR_FLG = "D";
        DDRVSTRN.AMT = DDCUTRVS.VS_SPAMT;
        DDRVSTRN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVSTRN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTVSTRN();
        if (pgmRtn) return;
    }
    public void B035_VSAC_CR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUTRVS.VS_CRAC);
        CEP.TRC(SCCGWA, DDCUTRVS.TXCCY);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_OPT);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_SPAMT);
        IBS.init(SCCGWA, DDRVSTRN);
        DDRVSTRN.KEY.VS_AC = DDCUTRVS.VS_CRAC;
        DDRVSTRN.KEY.SPECAL_TYP = "" + DDCUTRVS.VS_OPT;
        JIBS_tmp_int = DDRVSTRN.KEY.SPECAL_TYP.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) DDRVSTRN.KEY.SPECAL_TYP = "0" + DDRVSTRN.KEY.SPECAL_TYP;
        DDRVSTRN.CCY = DDCUTRVS.TXCCY;
        DDRVSTRN.CCY_TYP = DDCUTRVS.CCY_TYP;
        DDRVSTRN.KEY.DR_CR_FLG = "C";
        DDRVSTRN.AMT = DDCUTRVS.VS_SPAMT;
        DDRVSTRN.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVSTRN.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DDTVSTRN();
        if (pgmRtn) return;
    }
    public void B036_SPFLG_1_DR_PROC() throws IOException,SQLException,Exception {
        T000_READ_DDTVSTRN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            B035_VSAC_DR_PROC();
            if (pgmRtn) return;
        } else {
            T000_READUP_DDTVSTRN();
            if (pgmRtn) return;
            WS_SPAMT = DDRVSTRN.AMT + DDCUTRVS.VS_SPAMT;
            DDRVSTRN.AMT = WS_SPAMT;
            DDRVSTRN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVSTRN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTVSTRN();
            if (pgmRtn) return;
        }
    }
    public void B036_SPFLG_1_CR_PROC() throws IOException,SQLException,Exception {
        T000_READ_DDTVSTRN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            B035_VSAC_CR_PROC();
            if (pgmRtn) return;
        } else {
            T000_READUP_DDTVSTRN();
            if (pgmRtn) return;
            WS_SPAMT = DDRVSTRN.AMT + DDCUTRVS.VS_SPAMT;
            DDRVSTRN.AMT = WS_SPAMT;
            DDRVSTRN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVSTRN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTVSTRN();
            if (pgmRtn) return;
        }
        WS_VS_AC = DDCUTRVS.VS_CRAC;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        WS_MARGIN_BAL = DDRCCY.MARGIN_BAL + DDCUTRVS.VS_AMT;
        DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B036_SPFLG_2_DR_PROC() throws IOException,SQLException,Exception {
        if (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC)) {
            B037_SPAMT_DR_PROC();
            if (pgmRtn) return;
        } else if (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_CRAC)) {
            DDRVSTRN.KEY.DR_CR_FLG = "C";
            B037_SPAMT_DR_PROC();
            if (pgmRtn) return;
            WS_VS_AC = DDCUTRVS.VS_DRAC;
            B037_MARGIN_DR_PROC();
            if (pgmRtn) return;
        } else if (!DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC) 
                && (!DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_CRAC))) {
            DDRVSTRN.KEY.DR_CR_FLG = "C";
            B037_SPAMT_DR_PROC();
            if (pgmRtn) return;
            WS_VS_AC = DDCUTRVS.VS_DRAC;
            B037_MARGIN_DR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B036_SPFLG_2_CR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUTRVS.OLD_CRAC);
        if (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC)) {
            B037_SPAMT_CR_PROC();
            if (pgmRtn) return;
            WS_VS_AC = DDCUTRVS.VS_CRAC;
            B037_MARGIN_DR_PROC();
            if (pgmRtn) return;
        } else if (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_CRAC)) {
            DDRVSTRN.KEY.DR_CR_FLG = "D";
            B037_SPAMT_CR_PROC();
            if (pgmRtn) return;
        } else if (!DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC) 
                && (!DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_CRAC))) {
            DDRVSTRN.KEY.VS_AC = DDCUTRVS.OLD_CRAC;
            DDRVSTRN.KEY.DR_CR_FLG = "D";
            B037_SPAMT_CR_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SPAMT_INVAL);
        }
    }
    public void B037_SPAMT_DR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.VS_AC);
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.SPECAL_TYP);
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.DR_CR_FLG);
        T000_READUP_DDTVSTRN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_RECORD_NOT_EXIST);
        } else {
            if (DDCUTRVS.VS_SPAMT > DDRVSTRN.AMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_NOT_ENOUGH);
            }
            WS_SPAMT = DDRVSTRN.AMT - DDCUTRVS.VS_SPAMT;
            DDRVSTRN.AMT = WS_SPAMT;
            DDRVSTRN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVSTRN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTVSTRN();
            if (pgmRtn) return;
        }
    }
    public void B037_SPAMT_CR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.VS_AC);
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.SPECAL_TYP);
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.DR_CR_FLG);
        T000_READUP_DDTVSTRN();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_RECORD_NOT_EXIST);
        } else {
            if (DDCUTRVS.VS_SPAMT > DDRVSTRN.AMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_NOT_ENOUGH);
            }
            WS_SPAMT = DDRVSTRN.AMT - DDCUTRVS.VS_SPAMT;
            DDRVSTRN.AMT = WS_SPAMT;
            DDRVSTRN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRVSTRN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTVSTRN();
            if (pgmRtn) return;
        }
    }
    public void B037_MARGIN_DR_PROC() throws IOException,SQLException,Exception {
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        WS_MARGIN_BAL = DDRCCY.MARGIN_BAL - DDCUTRVS.VS_AMT;
        DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B040_CANCLE_DRAW_PROC() throws IOException,SQLException,Exception {
        if (DDCUTRVS.VS_SPFLG == '2' 
            && (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC))) {
        } else {
            B041_CHECK_DRBAL_CAL_PROC();
            if (pgmRtn) return;
        }
        B041_VSTRN_DRBAL_CAL_PROC();
        if (pgmRtn) return;
        if (WS_CR_ENTY_TYP == '4') {
            if (DDCUTRVS.VS_SPFLG == '2' 
                && (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC))) {
                B060_BP_NFHIS_PROC_CR();
                if (pgmRtn) return;
            } else {
                B050_FIN_TX_HIS_PROC_CR();
                if (pgmRtn) return;
            }
        }
        if (DDCUTRVS.VS_SPFLG == '2' 
            && (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC))) {
        } else {
            B041_CHECK_CRBAL_CAL_PROC();
            if (pgmRtn) return;
        }
        B041_VSTRN_CRBAL_CAL_PROC();
        if (pgmRtn) return;
        if (WS_DR_ENTY_TYP == '4') {
            if (DDCUTRVS.VS_SPFLG == '2' 
                && (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC))) {
                B060_BP_NFHIS_PROC_DR();
                if (pgmRtn) return;
            } else {
                B050_FIN_TX_HIS_PROC_DR();
                if (pgmRtn) return;
            }
        }
    }
    public void B041_CHECK_DRBAL_CAL_PROC() throws IOException,SQLException,Exception {
        WS_VS_AC = DDCUTRVS.VS_DRAC;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        if (WS_DR_ENTY_TYP == '4') {
            WS_DR_CURR_BAL = DDRCCY.CURR_BAL + DDCUTRVS.VS_AMT;
            DDRCCY.CURR_BAL = WS_DR_CURR_BAL;
        } else {
            WS_MARGIN_BAL = DDRCCY.MARGIN_BAL - DDCUTRVS.VS_AMT;
            CEP.TRC(SCCGWA, DDRCCY.CURR_BAL);
            DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B041_VSTRN_DRBAL_CAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUTRVS.VS_OPT);
        if (DDCUTRVS.VS_OPT != ' ') {
            IBS.init(SCCGWA, DDRVSTRN);
            DDRVSTRN.KEY.VS_AC = DDCUTRVS.VS_DRAC;
            DDRVSTRN.KEY.SPECAL_TYP = "" + DDCUTRVS.VS_OPT;
            JIBS_tmp_int = DDRVSTRN.KEY.SPECAL_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) DDRVSTRN.KEY.SPECAL_TYP = "0" + DDRVSTRN.KEY.SPECAL_TYP;
            DDRVSTRN.KEY.DR_CR_FLG = "D";
            T000_READ_DDTVSTRN();
            if (pgmRtn) return;
            if (DDCUTRVS.VS_SPFLG == '1') {
                B041_SPFLG_1_CAL_PROC();
                if (pgmRtn) return;
            } else if (DDCUTRVS.VS_SPFLG == '2') {
                B041_SPFLG_2_DR_CAL_PROC();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SPAMT_INVAL);
            }
        }
    }
    public void B041_CHECK_CRBAL_CAL_PROC() throws IOException,SQLException,Exception {
        WS_VS_AC = DDCUTRVS.VS_CRAC;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        if (WS_DR_ENTY_TYP == '4') {
            WS_CR_CURR_BAL = DDRCCY.CURR_BAL - DDCUTRVS.VS_AMT;
            DDRCCY.CURR_BAL = WS_CR_CURR_BAL;
        } else {
            WS_MARGIN_BAL = DDRCCY.MARGIN_BAL + DDCUTRVS.VS_AMT;
            DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
        }
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B041_VSTRN_CRBAL_CAL_PROC() throws IOException,SQLException,Exception {
        if (DDCUTRVS.VS_OPT != ' ') {
            IBS.init(SCCGWA, DDRVSTRN);
            DDRVSTRN.KEY.VS_AC = DDCUTRVS.VS_CRAC;
            DDRVSTRN.KEY.SPECAL_TYP = "" + DDCUTRVS.VS_OPT;
            JIBS_tmp_int = DDRVSTRN.KEY.SPECAL_TYP.length();
            for (int i=0;i<1-JIBS_tmp_int;i++) DDRVSTRN.KEY.SPECAL_TYP = "0" + DDRVSTRN.KEY.SPECAL_TYP;
            DDRVSTRN.KEY.DR_CR_FLG = "C";
            CEP.TRC(SCCGWA, DDRVSTRN.KEY.VS_AC);
            CEP.TRC(SCCGWA, DDRVSTRN.KEY.SPECAL_TYP);
            T000_READ_DDTVSTRN();
            if (pgmRtn) return;
            if (DDCUTRVS.VS_SPFLG == '1') {
                B041_SPFLG_1_CAL_PROC();
                if (pgmRtn) return;
                B047_MARGIN_BAL_1_PROC();
                if (pgmRtn) return;
            } else if (DDCUTRVS.VS_SPFLG == '2') {
                B041_SPFLG_2_CR_CAL_PROC();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SPAMT_INVAL);
            }
        }
    }
    public void B041_SPFLG_1_CAL_PROC() throws IOException,SQLException,Exception {
        T000_READUP_DDTVSTRN();
        if (pgmRtn) return;
        WS_SPAMT = DDRVSTRN.AMT - DDCUTRVS.VS_SPAMT;
        DDRVSTRN.AMT = WS_SPAMT;
        DDRVSTRN.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRVSTRN.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTVSTRN();
        if (pgmRtn) return;
    }
    public void B047_MARGIN_BAL_1_PROC() throws IOException,SQLException,Exception {
        WS_VS_AC = DDCUTRVS.VS_CRAC;
        T000_READUP_DDTCCY();
        if (pgmRtn) return;
        WS_MARGIN_BAL = DDRCCY.MARGIN_BAL - DDCUTRVS.VS_AMT;
        DDRCCY.MARGIN_BAL = WS_MARGIN_BAL;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B041_SPFLG_2_DR_CAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.VS_AC);
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.SPECAL_TYP);
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.DR_CR_FLG);
        if (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC)) {
            CEP.TRC(SCCGWA, "CR125 ");
            B047_SPAMT_CAL_PROC();
            if (pgmRtn) return;
        } else if (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_CRAC)) {
            CEP.TRC(SCCGWA, "HNN002");
            DDRVSTRN.KEY.DR_CR_FLG = "C";
            B047_SPAMT_CAL_PROC();
            if (pgmRtn) return;
            WS_VS_AC = DDCUTRVS.VS_DRAC;
            B047_MARGIN_CR_PROC();
            if (pgmRtn) return;
        } else if (!DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC) 
                && (!DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_CRAC))) {
            DDRVSTRN.KEY.DR_CR_FLG = "C";
            B047_SPAMT_CAL_PROC();
            if (pgmRtn) return;
            WS_VS_AC = DDCUTRVS.VS_DRAC;
            B047_MARGIN_CR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B041_SPFLG_2_CR_CAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "XXXXXXXX");
        CEP.TRC(SCCGWA, DDCUTRVS.OLD_CRAC);
        CEP.TRC(SCCGWA, DDCUTRVS.VS_DRAC);
        if (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC)) {
            B047_SPAMT_CAL_PROC();
            if (pgmRtn) return;
            WS_VS_AC = DDCUTRVS.VS_CRAC;
            B047_MARGIN_CR_PROC();
            if (pgmRtn) return;
        } else if (DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_CRAC)) {
            DDRVSTRN.KEY.DR_CR_FLG = "D";
            B047_SPAMT_CAL_PROC();
            if (pgmRtn) return;
        } else if (!DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_DRAC) 
                && (!DDCUTRVS.OLD_CRAC.equalsIgnoreCase(DDCUTRVS.VS_CRAC))) {
            DDRVSTRN.KEY.VS_AC = DDCUTRVS.OLD_CRAC;
            DDRVSTRN.KEY.DR_CR_FLG = "D";
            B047_SPAMT_CAL_PROC();
            if (pgmRtn) return;
        }
    }
    public void B047_SPAMT_CAL_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.VS_AC);
        CEP.TRC(SCCGWA, DDRVSTRN.KEY.SPECAL_TYP);
