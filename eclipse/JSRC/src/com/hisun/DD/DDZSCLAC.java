package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQIA;
import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPCFCSTS;
import com.hisun.BP.BPCFX;
import com.hisun.BP.BPCOCLWD;
import com.hisun.BP.BPCPFPDT;
import com.hisun.BP.BPCPLOSS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPOCAC;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSLOSS;
import com.hisun.BP.BPCSOCAC;
import com.hisun.BP.BPRLOSS;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICGAGA_AGENT_AREA_AGENT_AREA;
import com.hisun.CI.CICMSG_ERROR_MSG;
import com.hisun.CI.CICQACAC;
import com.hisun.CI.CICQACRI;
import com.hisun.CI.CICQACRL;
import com.hisun.CI.CICSAGEN;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCCUCINF;
import com.hisun.DC.DCCUPSWM;
import com.hisun.DC.DCRCDDAT;
import com.hisun.DC.DCRINRCD;
import com.hisun.GD.GDRSTAC;
import com.hisun.LN.LNCSICOT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCUCACK;

public class DDZSCLAC {
    boolean pgmRtn = false;
    String K_CLAC_FMT = "DD510";
    String K_LF_CHQ_FMT = "DD813";
    String K_APP_MMO = "DD";
    String K_CHK_STS_TBL = "5402";
    String K_HIS_REMARKS = "CLOSE ACCOUNT";
    String K_HIS_MMO = "P115";
    String K_HIS_COPYBOOK_NAME = "DDCSCLAC";
    String K_HIS_TX_CD = "0111803";
    String CPN_I_PSBK_PROC = "DD-I-PSBK-PROC";
    String CPN_DCZUAINQ = "DC-U-CARD-AC-INQ";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String CPN_DDZSIACC = "DD-SVR-DDZSIACC";
    String K_DDEG4 = "DDEG4";
    String K_DDEG5 = "DDEG5";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CHQ_CNT = 0;
    short WS_CHQ_NO = 0;
    short WS_CNT = 0;
    short WS_CNT1 = 0;
    short WS_CNT2 = 0;
    char WS_CHQ_TYPE = ' ';
    short WS_STR_CHQ_NO = 0;
    short WS_END_CHQ_NO = 0;
    short WS_A_CNT = 0;
    short WS_B_CNT = 0;
    short WS_C_CNT = 0;
    short WS_D_CNT = 0;
    short WS_E_CNT = 0;
    short WS_F_CNT = 0;
    String WS_CHQ_STS = " ";
    double WS_AMT = 0;
    double WS_PB_AMT = 0;
    short WS_DAYS = 0;
    char WS_RCCY_END = ' ';
    String WS_CARD_NO = " ";
    String WS_REL_AC = " ";
    double WS_LOSS_AMT = 0;
    double WS_RATE = 0;
    String WS_PROD_CD = " ";
    int WS_LOS_DT = 0;
    int WS_LOS_DT1 = 0;
    short WS_LOS_DAYS = 0;
    DDZSCLAC_WS_CHQ_INFO WS_CHQ_INFO = new DDZSCLAC_WS_CHQ_INFO();
    char WS_UNUSE_CHQ = ' ';
    char WS_CHQ_FLG = ' ';
    char WS_CCY_FLG = ' ';
    char WS_VCH_FLG = ' ';
    char WS_LFCHQ_FLG = ' ';
    char WS_CLS_FLG = ' ';
    char WS_FRG_CCY = ' ';
    char WS_MCAD_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSACR CICSACR = new CICSACR();
    CICACCU CICACCU = new CICACCU();
    DDCIPSBK DDCIPSBK = new DDCIPSBK();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DDCOCLAC DDCOCLAC = new DDCOCLAC();
    BPCPFPDT BPCPFPDT = new BPCPFPDT();
    CICCAGT CICCAGT = new CICCAGT();
    LNCSICOT LNCSICOT = new LNCSICOT();
    TDCUCACK TDCUCACK = new TDCUCACK();
    DDCO510 DDCO510 = new DDCO510();
    DDCOLFCQ DDCOLFCQ = new DDCOLFCQ();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DDCSIACC DDCSIACC = new DDCSIACC();
    CICSACAC CICSACAC = new CICSACAC();
    DDCUCCCY DDCUCCCY = new DDCUCCCY();
    DCCSCINF DCCSCINF = new DCCSCINF();
    DCCUDDCK DCCUDDCK = new DCCUDDCK();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCPLOSS BPCPLOSS = new BPCPLOSS();
    BPRLOSS BPRLOSS = new BPRLOSS();
    EQCQACT EQCQACT = new EQCQACT();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    DDCUPINT DDCUPINT = new DDCUPINT();
    AICPQIA AICPQIA = new AICPQIA();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCPCDCK DCCPCDCK = new DCCPCDCK();
    DDRMCAD DDRMCAD = new DDRMCAD();
    BPCFX BPCFX = new BPCFX();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DDCPBLST DDCPBLST = new DDCPBLST();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCUTBAL DCCUTBAL = new DCCUTBAL();
    CICCUST CICCUST = new CICCUST();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACAC CICFACAC = new CICQACAC();
    CICQACRL CICQACRL = new CICQACRL();
    GDCRSTAC GDCRSTAC = new GDCRSTAC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DDCSMRAT DDCSMRAT = new DDCSMRAT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    EACSBINQ EACSBINQ = new EACSBINQ();
    EACO890 EACO890 = new EACO890();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICSACRL CICSACRL = new CICSACRL();
    BPCPOCAC BPCPOCAC = new BPCPOCAC();
    EACSBIND EACSBIND = new EACSBIND();
    LNCSETLM LNCSETLM = new LNCSETLM();
    DCRCPARM DCRCPARM = new DCRCPARM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRVCH DDRVCH = new DDRVCH();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DCRINRCD DCRINRCD = new DCRINRCD();
    GDRSTAC GDRSTAC = new GDRSTAC();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSCLAC DDCSCLAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCLAC DDCSCLAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCLAC = DDCSCLAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCLAC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
        IBS.init(SCCGWA, DDRMST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B015_CHK_CARD_PROC_N();
        if (pgmRtn) return;
        B065_CHK_LN_CONTRACT();
        if (pgmRtn) return;
        B060_GET_CUS_INF_PROC();
        if (pgmRtn) return;
        B030_GET_AC_INF();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            B035_CHK_AC_STS();
            if (pgmRtn) return;
        }
        B036_GET_PSBK_INF();
        if (pgmRtn) return;
        B070_CHK_STS_TBL_PROC();
        if (pgmRtn) return;
        B090_CHK_PAY_MTH_PROC();
        if (pgmRtn) return;
        B095_POST_INT_PROC();
        if (pgmRtn) return;
        if (DDCSCLAC.BAT_FLG == 'B') {
            B096_BAT_CHECK();
            if (pgmRtn) return;
        }
        if (DDCSCLAC.TRF_FLG == 'O') {
            if (DDCSCLAC.CLVR_FLG != '1') {
                B100_TRF_O_INQ_AI_AC_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSCLAC.BV_TYP == '1') {
            if (DDCSCLAC.LOSS_NO.trim().length() == 0) {
                B120_CHK_CARD_INF_PROC();
                if (pgmRtn) return;
            }
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(15 - 1, 15 + 1 - 1).equalsIgnoreCase("1")) {
                WS_CNT2 = 1;
                while (WS_CNT2 <= 4 
                    && DDCSCLAC.CARD_INFO[WS_CNT2-1].CARD_NO.trim().length() != 0) {
                    WS_CARD_NO = " ";
                    WS_CARD_NO = DDCSCLAC.CARD_INFO[WS_CNT2-1].CARD_NO;
                    CEP.TRC(SCCGWA, WS_CARD_NO);
                    WS_CNT2 += 1;
                }
            }
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("111804")) {
            R000_CHK_LOST_ORG();
            if (pgmRtn) return;
            R000_CHK_PBLS_CONDITION();
            if (pgmRtn) return;
        }
        B140_CLEAR_CCY_PROC();
        if (pgmRtn) return;
        B150_CHK_UNCLEAR_CCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CLS_FLG);
        if (WS_CLS_FLG == 'Y') {
            B037_CARD_PSBK_CHECK();
            if (pgmRtn) return;
            B038_CHECK_BIND_INFO();
            if (pgmRtn) return;
            B055_CHK_AC_ARREARAGE();
            if (pgmRtn) return;
            B060_CHK_CI_CONTRACT();
            if (pgmRtn) return;
            B066_CHK_TD_CONTRACT();
            if (pgmRtn) return;
            if (DDRMST.AC_TYPE != ' ' 
                && DDRMST.AC_TYPE != 'N' 
                && DDRMST.AC_TYPE != 'J' 
                && DDRMST.AC_TYPE != 'O') {
                B067_CHK_GD_CONTRACT();
                if (pgmRtn) return;
            }
            B110_PSB_CLOSE_PROC();
            if (pgmRtn) return;
            B160_CLEAR_CARD_PROC();
            if (pgmRtn) return;
            if (DDCSCLAC.LOSS_NO.trim().length() > 0) {
                B165_UPD_LOSS_INFO();
                if (pgmRtn) return;
            }
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                B170_UPD_MST_INFO();
                if (pgmRtn) return;
                B190_UPD_CI_INFO();
                if (pgmRtn) return;
            }
            B210_UPD_REGISTER();
            if (pgmRtn) return;
            B230_NON_FIN_HIS_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
            if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
                B240_AGENT_INF_PORC();
                if (pgmRtn) return;
            }
        }
        if (DDCSCLAC.CLVR_FLG == '1') {
            IBS.init(SCCGWA, EACSBINQ);
            EACSBINQ.CARD_NO = DDCSCLAC.DR_CARD;
            S000_CALL_EAZSBINQ();
            if (pgmRtn) return;
            if (EACSBINQ.AC_ARRAY[1-1].CON_AC.trim().length() > 0 
                || EACSBINQ.AC_ARRAY[2-1].CON_AC.trim().length() > 0 
                || EACSBINQ.AC_ARRAY[3-1].CON_AC.trim().length() > 0 
                || EACSBINQ.AC_ARRAY[4-1].CON_AC.trim().length() > 0 
                || EACSBINQ.AC_ARRAY[5-1].CON_AC.trim().length() > 0) {
                IBS.init(SCCGWA, EACSBIND);
                EACSBIND.FUNC = 'C';
                EACSBIND.CARD_NO = DDCSCLAC.DR_CARD;
                S000_CALL_EAZSBIND();
                if (pgmRtn) return;
            }
        }
        if (DDCSCLAC.CLVR_FLG != '1') {
            B250_OUTPUT_DATA_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCLAC.AC_NO);
        CEP.TRC(SCCGWA, DDCSCLAC.CCY);
        CEP.TRC(SCCGWA, DDCSCLAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCLAC.DR_CARD);
        CEP.TRC(SCCGWA, DDCSCLAC.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSCLAC.DRW_PSW);
        CEP.TRC(SCCGWA, DDCSCLAC.REMARK);
        CEP.TRC(SCCGWA, DDCSCLAC.BAT_FLG);
        CEP.TRC(SCCGWA, DDCSCLAC.RVS_NO);
        CEP.TRC(SCCGWA, DDCSCLAC.RLT_AC);
        CEP.TRC(SCCGWA, DDCSCLAC.TRF_CCY);
        CEP.TRC(SCCGWA, DDCSCLAC.TRF_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCLAC.CLVR_FLG);
        CEP.TRC(SCCGWA, DDCSCLAC.HOLDER_IDTYP);
        CEP.TRC(SCCGWA, DDCSCLAC.HOLDER_IDNO);
        CEP.TRC(SCCGWA, DDCSCLAC.HOLDER_CINO);
        CEP.TRC(SCCGWA, DDCSCLAC.HOLDER_NAME);
        if (DDCSCLAC.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCLAC.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCLAC.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCSCLAC.TRF_FLG);
        if (DDCSCLAC.TRF_FLG == 'T' 
            && DDCSCLAC.TO_BVTYP != '3') {
            if (DDCSCLAC.TRF_CCY.trim().length() == 0) {
                DDCSCLAC.TRF_CCY = DDCSCLAC.CCY;
            }
            if (DDCSCLAC.TRF_CCY_TYPE == ' ') {
                DDCSCLAC.TRF_CCY_TYPE = DDCSCLAC.CCY_TYPE;
            }
            if (!DDCSCLAC.CCY.equalsIgnoreCase(DDCSCLAC.TRF_CCY)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_NOT_SAME);
            }
            if (DDCSCLAC.CCY_TYPE == '1' 
                && DDCSCLAC.TRF_CCY_TYPE == '2') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_ERROR);
            }
        }
        CEP.TRC(SCCGWA, DDCSCLAC.DR_CARD);
        CEP.TRC(SCCGWA, DDCSCLAC.TO_CARD);
        CEP.TRC(SCCGWA, DDCSCLAC.AC_NO);
        CEP.TRC(SCCGWA, DDCSCLAC.TRF_AC);
        CEP.TRC(SCCGWA, DDCSCLAC.LOSS_NO);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        if ((DDCSCLAC.DR_CARD.trim().length() > 0 
            && DDCSCLAC.TO_CARD.trim().length() > 0 
            && DDCSCLAC.DR_CARD.equalsIgnoreCase(DDCSCLAC.TO_CARD)) 
            || (DDCSCLAC.AC_NO.trim().length() > 0 
            && DDCSCLAC.TRF_AC.trim().length() > 0 
            && DDCSCLAC.AC_NO.equalsIgnoreCase(DDCSCLAC.TRF_AC)) 
            || (DDCSCLAC.TRF_AC.trim().length() > 0 
            && DCCPACTY.OUTPUT.STD_AC.equalsIgnoreCase(DDCSCLAC.TRF_AC))) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CL_TRF_CARD_AC_N_EQU;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCLAC.CLVR_FLG == '1') {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = DDCSCLAC.DR_CARD;
            CEP.TRC(SCCGWA, CICCUST.DATA.AGR_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
            if ((!CICCUST.O_DATA.O_ID_TYPE.equalsIgnoreCase(DDCSCLAC.HOLDER_IDTYP)) 
                || (!CICCUST.O_DATA.O_ID_NO.equalsIgnoreCase(DDCSCLAC.HOLDER_IDNO))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_NOT_MACTH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DDCSCLAC.DR_CARD;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            if (DCRCDDAT.CARD_MEDI != '4') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DC_NOTVR_CANT_DO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B015_CHK_CARD_PROC_N() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CEP.TRC(SCCGWA, "----YYYY----");
        CICQACRL.DATA.AC_NO = DDCSCLAC.AC_NO;
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND)) {
            WS_REL_AC = DDCSCLAC.AC_NO;
        }
        if (CICQACRL.RC.RC_CODE == 0) {
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        }
        CEP.TRC(SCCGWA, WS_REL_AC);
        if (WS_REL_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_REL_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCSCLAC.AC_NO;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CICQACAC, CICFACAC);
        CEP.TRC(SCCGWA, CICFACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        CEP.TRC(SCCGWA, CICFACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC);
        CEP.TRC(SCCGWA, CICFACAC.O_DATA.O_ACAC_DATA.O_CR_FLG);
    }
    public void B060_GET_CUS_INF_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = CICQACRI.O_DATA.O_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B030_GET_AC_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = DDCSCLAC.AC_NO;
            T000_READ_UPDATE_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCSCLAC.AC_NO);
            CEP.TRC(SCCGWA, DDRMST.CARD_FLG);
            CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
            CEP.TRC(SCCGWA, DDRMST.CROS_DR_FLG);
            CEP.TRC(SCCGWA, DDRMST.CROS_CR_FLG);
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD);
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1));
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(41 - 1, 41 + 1 - 1));
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            CEP.TRC(SCCGWA, "DC");
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DDCSCLAC.AC_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.JOIN_CUS_FLG);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (!JIBS_tmp_str[0].equalsIgnoreCase("0265857") 
                && (DCCUCINF.PROD_CD.equalsIgnoreCase("CAD52000") 
                || DCCUCINF.PROD_CD.equalsIgnoreCase("CAD54000"))) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ELAC_CLOSE_NOT_ALLOW);
            }
        }
    }
    public void B035_CHK_AC_STS() throws IOException,SQLException,Exception {
        if (DDRMST.AC_STS == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
            && DDCSCLAC.BAT_FLG == 'B') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_SOCIAL_GET_ALR);
        }
    }
    public void B055_CHK_AC_ARREARAGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFPDT);
        BPCPFPDT.INPUT.AC = DDCSCLAC.AC_NO;
        S000_CALL_BPZPFPDT();
        if (pgmRtn) return;
        if (BPCPFPDT.OUTPUT.PCHG_FLG == 'Y' 
            || BPCPFPDT.OUTPUT.UNCHG_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_ARREARGE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_CHK_EQ_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCQACT);
        EQCQACT.DATA.EQ_BKID = "01";
        EQCQACT.DATA.DIV_AC = DDCSCLAC.AC_NO;
        EQCQACT.FUNC = 'F';
        S000_CALL_EQZQACT();
        if (pgmRtn) return;
        if (EQCQACT.DATA.FOUND_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_EQ_AC_NOT_CLOSE);
        }
    }
    public void B060_CHK_CI_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCAGT);
        CICCAGT.DATA.CI_NO = CICACCU.DATA.CI_NO;
        CICCAGT.FUNC = 'B';
        CICCAGT.DATA.ENTY_TYP = '1';
        CICCAGT.DATA.AGR_NO = DDCSCLAC.AC_NO;
        S000_CALL_CIZCAGT();
        if (pgmRtn) return;
    }
    public void B065_CHK_LN_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSETLM);
        LNCSETLM.FUNC = 'I';
        LNCSETLM.DD_AC = DDCSCLAC.AC_NO;
        LNCSETLM.CCY = DDCSCLAC.CCY;
        LNCSETLM.CCY_TYP = DDCSCLAC.CCY_TYPE;
        CEP.TRC(SCCGWA, LNCSETLM.DD_AC);
        CEP.TRC(SCCGWA, LNCSETLM.CCY);
        CEP.TRC(SCCGWA, LNCSETLM.CCY_TYP);
        S000_CALL_LNZSETLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSETLM.CON_FLG);
        if (LNCSETLM.CON_FLG == 'N') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_LN_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B066_CHK_TD_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUCACK);
        TDCUCACK.AC_NO = DDCSCLAC.AC_NO;
        S000_CALL_TDZUCACK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCUCACK.PI_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.GK_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.ZC_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.FB_FLG);
        CEP.TRC(SCCGWA, TDCUCACK.FOUND_FLG);
        if (TDCUCACK.PI_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_FX_ACCT);
        }
        if (TDCUCACK.GK_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_GK_ACCT);
        }
        if (TDCUCACK.ZC_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_ZC_ACCT);
        }
        if (TDCUCACK.FB_FLG == 'Y') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IS_FB_ACCT);
        }
        CEP.TRC(SCCGWA, TDCUCACK.FOUND_FLG);
        if (TDCUCACK.FOUND_FLG == 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_TD_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B067_CHK_GD_CONTRACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        GDCRSTAC.FUNC = 'B';
        GDCRSTAC.OPT = 'C';
        GDRSTAC.ST_AC = DDCSCLAC.AC_NO;
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        if (pgmRtn) return;
        GDCRSTAC.OPT = 'R';
        GDRSTAC.ST_AC = DDCSCLAC.AC_NO;
        CEP.TRC(SCCGWA, GDRSTAC.ST_AC);
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_GD_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        GDCRSTAC.OPT = 'E';
        IBS.init(SCCGWA, GDCRSTAC);
        IBS.init(SCCGWA, GDRSTAC);
        GDCRSTAC.FUNC = 'B';
        GDCRSTAC.OPT = 'T';
        GDRSTAC.INT_AC = DDCSCLAC.AC_NO;
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        if (pgmRtn) return;
        GDCRSTAC.OPT = 'R';
        GDRSTAC.INT_AC = DDCSCLAC.AC_NO;
        CEP.TRC(SCCGWA, GDRSTAC.INT_AC);
        GDCRSTAC.REC_PTR = GDRSTAC;
        GDCRSTAC.REC_LEN = 401;
        S000_CALL_GDZRSTAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDCRSTAC.RC);
        if (GDCRSTAC.RC.RC_CODE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_HAVE_GD_CONT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        GDCRSTAC.OPT = 'E';
        S000_CALL_GDZRSTAC();
        if (pgmRtn) return;
    }
    public void B070_CHK_STS_TBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_APP_MMO;
        BPCFCSTS.TBL_NO = K_CHK_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICCUST.O_DATA.O_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + DDRMST.AC_STS_WORD + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        if (DDCSCLAC.BV_TYP == '1') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 105 - 1) + "0000" + BPCFCSTS.STATUS_WORD.substring(105 + 4 - 1);
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + BPCFCSTS.STATUS_WORD.substring(101 + 99 - 1);
        }
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CNTRCT_TYP);
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (CICQACRI.O_DATA.O_CNTRCT_TYP.equalsIgnoreCase("103") 
            && DDRMST.AC_STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 102 - 1) + "0" + BPCFCSTS.STATUS_WORD.substring(102 + 1 - 1);
        }
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 201 - 1) + "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + BPCFCSTS.STATUS_WORD.substring(201 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
        CEP.TRC(SCCGWA, BPCFCSTS.TBL_NO);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
        if (pgmRtn) return;
    }
    public void B090_CHK_PAY_MTH_PROC() throws IOException,SQLException,Exception {
        if (DDCSCLAC.BV_TYP == '2') {
            IBS.init(SCCGWA, DDCIMPAY);
            DDCIMPAY.AC = DDCSCLAC.AC_NO;
            CEP.TRC(SCCGWA, DDCIMPAY.AC);
            DDCIMPAY.FUNC = 'C';
            DDCIMPAY.PAY_MTH = '1';
            DDCIMPAY.PAY_MTH = DDCSCLAC.PAY_TYPE;
            DDCIMPAY.ID_TYPE = DDCSCLAC.PAY_ID_TYPE;
            DDCIMPAY.ID_NO = DDCSCLAC.PAY_ID_NO;
            DDCIMPAY.PSWD_OLD = DDCSCLAC.DRW_PSW;
            CEP.TRC(SCCGWA, DDCSCLAC.DRW_PSW);
            CEP.TRC(SCCGWA, DDCIMPAY.PSWD_OLD);
            S000_CALL_DDZIMPAY();
            if (pgmRtn) return;
        }
    }
    public void B096_BAT_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, DDRMST.OPEN_DP);
        CEP.TRC(SCCGWA, DDRMST.OWNER_BR);
        if (((BPCPQORG.ATTR == '3' 
            && DDRMST.OPEN_DP != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            && DDRMST.OPEN_DP != BPCPQORG.SUPR_BR) 
            || (BPCPQORG.ATTR != '3' 
            && DDRMST.OWNER_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TXN_BR_NOT_OPEN_BR);
        }
        CEP.TRC(SCCGWA, DDCSCLAC.AC_CNM);
        CEP.TRC(SCCGWA, DDCSCLAC.PAY_ID_TYPE);
        CEP.TRC(SCCGWA, DDCSCLAC.PAY_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        if ((!DDCSCLAC.PAY_ID_TYPE.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE)) 
            || (!DDCSCLAC.PAY_ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO)) 
            || (!DDCSCLAC.AC_CNM.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NM))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ID_INF_DISCREPANCY);
        }
    }
    public void B036_GET_PSBK_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRVCH);
        DDRVCH.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
        T000_READ_DDTVCH();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_NO);
        DDCSCLAC.PSBK_NO = DDRVCH.PSBK_NO;
        CEP.TRC(SCCGWA, DDRVCH.PSBK_STS);
        if (DDRVCH.PSBK_STS == 'W' 
            && DDRVCH.LOST_NO.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPLOSS);
            BPCPLOSS.DATA_INFO.LOS_NO = DDRVCH.LOST_NO;
            BPCPLOSS.INFO.FUNC = 'I';
            BPCPLOSS.INFO.INDEX_FLG = "1";
            IBS.CALLCPN(SCCGWA, "BP-P-QUERY-LOSS-INFO", BPCPLOSS);
            if (BPCPLOSS.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPLOSS.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCPLOSS.DATA_INFO.LOS_ORG);
            R000_GET_BBR_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.BBR);
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BBR);
            if (BPCPQORG.BBR != BPCPORUP.DATA_INFO.BBR) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_LOST_BR_NOT_MATCH);
            }
        }
    }
    public void R000_GET_BBR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCPLOSS.DATA_INFO.LOS_ORG;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B037_CARD_PSBK_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CARD-PSBK-CHECK");
        if (DDRMST.CARD_FLG == 'Y') {
            if (DDCSCLAC.BV_TYP == '2') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_M_USE_CARD_CLAC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSCLAC.BV_TYP == '1') {
                if (DDRVCH.PSBK_STS == 'N') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_M_CLEAR_PSBK_FIRST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B038_CHECK_BIND_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EACSBINQ);
        EACSBINQ.I_AC = DDCSCLAC.AC_NO;
        S000_CALL_EAZSBINQ();
        if (pgmRtn) return;
        if (EACSBINQ.END_SEQ > 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_EALINK_EXIST);
        }
    }
    public void B110_PSB_CLOSE_PROC() throws IOException,SQLException,Exception {
        if (DDCSCLAC.BV_TYP == '2') {
            IBS.init(SCCGWA, DDCIPSBK);
            DDCIPSBK.FUNC = 'D';
            DDCIPSBK.AC = DDCSCLAC.AC_NO;
            DDCIPSBK.PSBK_NO = DDCSCLAC.PSBK_NO;
            DDCIPSBK.UPT_MMO = K_HIS_MMO;
            S000_CALL_DDZIPSBK();
            if (pgmRtn) return;
        }
    }
    public void B115_DSD_UNUSE_CHQB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSIACC);
        DDCSIACC.AC = DDCSCLAC.AC_NO;
        DDCSIACC.FUNC = 'D';
        S000_CALL_DDZSIACC();
        if (pgmRtn) return;
    }
    public void B130_CHK_UNUSE_CHQB_PROC() throws IOException,SQLException,Exception {
        WS_CHQ_CNT = 0;
        T000_STARTBR_AC_CHQ_PROC();
        if (pgmRtn) return;
        T000_READNEXT_DDTCHQ();
        if (pgmRtn) return;
        WS_UNUSE_CHQ = 'N';
        WS_A_CNT = 1;
        WS_C_CNT = 1;
        WS_E_CNT = 1;
        WS_B_CNT = 0;
        WS_D_CNT = 0;
        WS_F_CNT = 0;
        IBS.init(SCCGWA, WS_CHQ_INFO);
        while (WS_CHQ_FLG != 'N') {
            if (DDRCHQ.KEY.STR_CHQ_NO.trim().length() == 0) WS_STR_CHQ_NO = 0;
            else WS_STR_CHQ_NO = Short.parseShort(DDRCHQ.KEY.STR_CHQ_NO);
            if (DDRCHQ.KEY.END_CHQ_NO.trim().length() == 0) WS_END_CHQ_NO = 0;
            else WS_END_CHQ_NO = Short.parseShort(DDRCHQ.KEY.END_CHQ_NO);
            WS_CHQ_CNT = WS_END_CHQ_NO - WS_STR_CHQ_NO + 1;
            CEP.TRC(SCCGWA, WS_CHQ_CNT);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.AC);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
            CEP.TRC(SCCGWA, DDRCHQ.KEY.END_CHQ_NO);
            if (DDRCHQ.KEY.STR_CHQ_NO.trim().length() == 0) WS_CHQ_NO = 0;
            else WS_CHQ_NO = Short.parseShort(DDRCHQ.KEY.STR_CHQ_NO);
            WS_CHQ_TYPE = DDRCHQ.KEY.CHQ_TYP;
            CEP.TRC(SCCGWA, WS_CHQ_NO);
            WS_CHQ_STS = DDRCHQ.CHQ_STS;
            WS_LFCHQ_FLG = 'N';
            for (WS_CNT = 1; WS_CNT <= WS_CHQ_CNT; WS_CNT += 1) {
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
                JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
                for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
                if ((DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).equalsIgnoreCase("N") 
                    || DDRCHQ.CHQ_STS.substring(WS_CNT - 1, WS_CNT + 1 - 1).trim().length() == 0)) {
                    if (DDRCHQ.KEY.CHQ_TYP == DPCPARMP.CASH_CHQ) {
                        WS_B_CNT += 1;
                        if (WS_B_CNT > 6) {
                            WS_A_CNT += 1;
                            WS_B_CNT = 1;
                        }
                        WS_CHQ_INFO.WS_AC_CCHQ[WS_A_CNT-1].REDEFINES32.WS_AC_CHQ_CDETIL[WS_B_CNT-1].WS_TMP_K1 = '/';
