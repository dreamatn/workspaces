package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.CM.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSTRAC {
    SimpleDateFormat JIBS_sdf;
    Date JIBS_date;
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DCTCDDAT_RD;
    String K_OUTPUT_FMT = "DD129";
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
    char WS_CARD_FLG = ' ';
    char WS_FR_AC_TYPE = ' ';
    String WS_N_FR_CARD = " ";
    String WS_N_TO_CARD = " ";
    String WS_FR_AC = " ";
    String WS_TO_AC = " ";
    String WS_DDZUCRAC_TS = " ";
    String WS_DDZUDRAC_TS = " ";
    String WS_BPZSEX_TS = " ";
    String WS_DDZUUPAY_TS = " ";
    String WS_BPZFAMTA_TS = " ";
    String WS_DCZPFTCK_TS = " ";
    String WS_AIZUUPIA_TS = " ";
    String WS_AIZPIAEV_TS = " ";
    String WS_CIZQACRI_TS = " ";
    String WS_CIZQACRL_TS = " ";
    String WS_BPZPCLWD_TS = " ";
    String WS_CIZACCU_TS = " ";
    String WS_AIZPQMIB_TS = " ";
    String WS_DCZPACTY_TS = " ";
    String WS_CIZQACAC_TS = " ";
    String WS_DDZSTRAC_TS = " ";
    String WS_FR_FRG_IND = " ";
    String WS_TO_FRG_IND = " ";
    String WS_PQORG_TRA_TYP = " ";
    double WS_FEE_AMT = 0;
    char WS_FR_CI_TYPE = ' ';
    char WS_TO_CI_TYPE = ' ';
    char WS_FR_ADSC_TYPE = ' ';
    char WS_TO_ADSC_TYPE = ' ';
    char WS_CI_TYPE1 = ' ';
    char WS_CI_TYPE2 = ' ';
    String WS_FR_AC_NO = " ";
    String WS_TO_AC_NO = " ";
    char WS_TO_AC_TYPE = ' ';
    String WS_PROD_CD = " ";
    String WS_CR_MMO = " ";
    String WS_DR_MMO = " ";
    int WS_FR_OWNER_BR = 0;
    int WS_TO_OWNER_BR = 0;
    String WS_FR_TRA_TYP = " ";
    String WS_TO_TRA_TYP = " ";
    DDZSTRAC_WS_HLD_NO WS_HLD_NO = new DDZSTRAC_WS_HLD_NO();
    char WS_REC_FLG = ' ';
    char WS_SAME_NAME_FLG = ' ';
    char WS_SOCIAL_CARD_FLG = ' ';
    char WS_AC_ROUTINE_FLG = ' ';
    char WS_HEI_FLG = ' ';
    char WS_BAI_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DDCOTRAC DDCOTRAC = new DDCOTRAC();
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
    DCCPACTY DCCPACTY = new DCCPACTY();
    CICQACAC CICQACAC = new CICQACAC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCUUPAY DDCUUPAY = new DDCUUPAY();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRI CICFACRI = new CICQACRI();
    CICQACRL CICQACRL = new CICQACRL();
    CICSPVS CICSPVS = new CICSPVS();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DDCSMREG DDCSMREG = new DDCSMREG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CMCSELAY CMCSELAY = new CMCSELAY();
    CMCS8840 CMCS8840 = new CMCS8840();
    DCCUHLD DCCUHLD = new DCCUHLD();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DDRMST DDRMST = new DDRMST();
    AIRMIB AIRMIB = new AIRMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    DDCSTRAC DDCSTRAC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DDCSTRAC DDCSTRAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSTRAC = DDCSTRAC;
        CEP.TRC(SCCGWA);
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_DDZSTRAC_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_DDZSTRAC_TS);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_DDZSTRAC_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_DDZSTRAC_TS);
        CEP.TRC(SCCGWA, "DDZSTRAC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSTRAC.TRK2_DAT);
        CEP.TRC(SCCGWA, DDCSTRAC.TRK3_DAT);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_REMARK);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_REMARK);
        B010_CHK_INPUT_DATA();
        B014_GET_STD_AC_INFO();
        B015_GET_IND_INF_PROC();
        B015_GET_AC_ADSC_TYPE_PROC();
        B015_GET_AC_PROC();
        B016_JUDGE_AC_TYPE_PROC();
    }
    public void B010_CHECK_CCY_TYPE_NOT_SAME() throws IOException,SQLException,Exception {
        if (DDCSTRAC.FR_CCY_TYPE == '1' 
            && DDCSTRAC.TO_CCY_TYPE == '2') {
            CEP.TRC(SCCGWA, "-- CHAO TO HUI --");
            B090_CCY_TYPE1_TYPE2_PROC();
        }
        if (DDCSTRAC.FR_CCY_TYPE == '2' 
            && DDCSTRAC.TO_CCY_TYPE == '1') {
            CEP.TRC(SCCGWA, "-- HUI TO CHAO --");
            B090_CCY_TYPE2_TYPE1_PROC();
        }
    }
    public void B000_REAL_TIME_TRANSFER() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSTRAC.FR_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_BV_TYPE);
        if (DDCSTRAC.FR_BV_TYPE == '3') {
            B041_CALL_AI_DR_UNT();
        } else {
            B040_WITHDRAW_PROC();
        }
        if (DDCSTRAC.TO_BV_TYPE == '3') {
            B051_CALL_AI_CR_UNT();
        } else {
            B050_DEPOSIT_PROC();
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B070_AGENT_INF_PORC();
        } else {
            if (DDCSTRAC.AGE_FLG == '1') {
                B070_AGENT_INF_PORC_TLR();
            }
        }
        CEP.TRC(SCCGWA, DDCSTRAC.REP_TYP);
        if (DDCSTRAC.REP_TYP == '1' 
            || DDCSTRAC.REP_TYP == '2' 
            || DDCSTRAC.REP_TYP == '3' 
            || DDCSTRAC.REP_TYP == '4') {
            B080_INTER_REP_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B060_OUTPUT_PROCESS();
        }
    }
    public void B015_GET_AC_ADSC_TYPE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        if (CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = CICFACRI.O_DATA.O_AGR_NO;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_NO);
            S000_CALL_DCZUCINF();
            WS_FR_ADSC_TYPE = DCCUCINF.ADSC_TYPE;
        } else {
            if (CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = CICFACRI.O_DATA.O_AGR_NO;
                T000_READ_DDTMST();
            }
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = CICQACRI.O_DATA.O_AGR_NO;
            S000_CALL_DCZUCINF();
            WS_TO_ADSC_TYPE = DCCUCINF.ADSC_TYPE;
        } else {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
                T000_READ_DDTMST();
            }
        }
    }
    public void B015_GET_AC_PROC() throws IOException,SQLException,Exception {
        if (!CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = CICFACRI.O_DATA.O_AGR_NO;
            CICQACAC.DATA.CCY_ACAC = DDCSTRAC.FR_CCY;
            CICQACAC.DATA.CR_FLG = DDCSTRAC.FR_CCY_TYPE;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR);
            WS_FR_AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            WS_PROD_CD = CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR;
            DDCSTRAC.O_DATA.PROD_CD = CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR;
        }
    }
    public void B016_JUDGE_AC_TYPE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, WS_FR_ADSC_TYPE);
        CEP.TRC(SCCGWA, WS_FR_CI_TYPE);
        CEP.TRC(SCCGWA, WS_TO_ADSC_TYPE);
        CEP.TRC(SCCGWA, WS_TO_CI_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.DELAY_FLG);
        if (DDCSTRAC.DELAY_FLG == ' ') {
            DDCSTRAC.DELAY_FLG = '0';
        }
        if (DDCSTRAC.DELAY_FLG != '0') {
            if ((WS_FR_ADSC_TYPE == 'P' 
                || WS_FR_CI_TYPE == '1') 
                && (WS_TO_ADSC_TYPE == 'P' 
                || WS_TO_CI_TYPE == '1')) {
                if (DDCSTRAC.FR_CCY.equalsIgnoreCase("156") 
                    && DDCSTRAC.TO_CCY.equalsIgnoreCase("156")) {
                    B000_DELAY_TRANSFER();
                } else {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_RMB_NO_DELAY_TXN);
                }
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_PER_NO_DELAY_TXN);
            }
        } else {
            B000_REAL_TIME_TRANSFER();
        }
    }
    public void B000_DELAY_TRANSFER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "HOLD";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1).trim().length() == 0) WS_HLD_NO.WS_HLD_DATE = 0;
        else WS_HLD_NO.WS_HLD_DATE = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 6 - 1));
        WS_HLD_NO.WS_HLD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        WS_HLD_NO.WS_HLD_SYSNO = 0;
        WS_HLD_NO.WS_HLD_SEQ = (int) BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, WS_HLD_NO);
        IBS.init(SCCGWA, DCCUHLD);
        DCCUHLD.DATA.HLD_NO = IBS.CLS2CPY(SCCGWA, WS_HLD_NO);
        if (CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = CICFACRI.O_DATA.O_AGR_NO;
            T000_READ_DCTCDDAT();
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_LNK_TYP);
            CEP.TRC(SCCGWA, DCRCDDAT.PRIM_CARD_NO);
            if (DCRCDDAT.CARD_LNK_TYP == '2') {
                CEP.TRC(SCCGWA, "SUB CARD");
                DCCUHLD.DATA.AC = DCRCDDAT.PRIM_CARD_NO;
            } else {
                CEP.TRC(SCCGWA, "NOT SUB CARD");
                DCCUHLD.DATA.AC = CICFACRI.O_DATA.O_AGR_NO;
            }
        } else {
            CEP.TRC(SCCGWA, "NOT CARD");
            DCCUHLD.DATA.AC = CICFACRI.O_DATA.O_AGR_NO;
        }
        DCCUHLD.DATA.AC = CICFACRI.O_DATA.O_AGR_NO;
        DCCUHLD.DATA.HLD_TYP = '2';
        DCCUHLD.DATA.SPR_TYP = '2';
        DCCUHLD.DATA.HLD_CLS = '8';
        DCCUHLD.DATA.CCY = DDCSTRAC.FR_CCY;
        DCCUHLD.DATA.CCY_TYP = DDCSTRAC.FR_CCY_TYPE;
        DCCUHLD.DATA.AMT = DDCSTRAC.FR_AMT;
        DCCUHLD.DATA.AMT += WS_FEE_AMT;
        DCCUHLD.DATA.RSN = "TRF DELAY";
        DCCUHLD.DATA.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCCUHLD.DATA.RMK = DDCSTRAC.TX_RMK;
        DCCUHLD.DATA.HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCCUHLD.DATA.PSWD = DDCSTRAC.PSWD;
        DCCUHLD.DATA.TRK_DAT2 = DDCSTRAC.TRK2_DAT;
        DCCUHLD.DATA.TRK_DAT3 = DDCSTRAC.TRK3_DAT;
        DCCUHLD.DATA.CHK_OPT = '3';
        if (DDCSTRAC.CHK_PSW == 'P') {
            DCCUHLD.DATA.CHK_OPT = '1';
        } else if (DDCSTRAC.CHK_PSW == 'T') {
            DCCUHLD.DATA.CHK_OPT = '2';
        } else if (DDCSTRAC.CHK_PSW == 'B') {
            DCCUHLD.DATA.CHK_OPT = '3';
        } else if (DDCSTRAC.CHK_PSW == 'N') {
            DCCUHLD.DATA.CHK_OPT = '4';
        }
        DCCUHLD.DATA.HLD_FLG = '1';
        CEP.TRC(SCCGWA, DCCUHLD.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DCCUHLD.DATA.RSN);
        S000_CALL_DCZUHLD();
        IBS.init(SCCGWA, CMCSELAY);
        IBS.init(SCCGWA, CMCS8840);
        CMCSELAY.FUNC = '1';
        CMCSELAY.TRF_TYP = DDCSTRAC.DELAY_FLG;
        CMCSELAY.HLD_NO = DCCUHLD.DATA.HLD_NO;
        if (DDCSTRAC.FR_CARD.trim().length() > 0) {
            CMCSELAY.TRO_AC = DDCSTRAC.FR_CARD;
        } else {
            CMCSELAY.TRO_AC = DDCSTRAC.FR_AC;
        }
        if (DDCSTRAC.TO_CARD.trim().length() > 0) {
            CMCSELAY.TRI_AC = DDCSTRAC.TO_CARD;
        } else {
            CMCSELAY.TRI_AC = DDCSTRAC.TO_AC;
        }
        CMCSELAY.CCY = DDCSTRAC.FR_CCY;
        CMCSELAY.CCY_TYP = DDCSTRAC.FR_CCY_TYPE;
        CMCSELAY.TRF_AMT = DDCSTRAC.FR_AMT;
        CMCSELAY.FEE_AMT = WS_FEE_AMT;
        CMCS8840.AC1 = CICFACRI.O_DATA.O_AGR_NO;
        CMCS8840.AC_TYP1 = 'X';
        CMCS8840.RMK1 = WS_DR_MMO;
        CMCS8840.CCY_NO1 = DDCSTRAC.FR_CCY;
        CMCS8840.CCY_TYP1 = DDCSTRAC.FR_CCY_TYPE;
        CMCS8840.BV_TYP1 = DDCSTRAC.FR_BV_TYPE;
        CMCS8840.AC2 = CICQACRI.O_DATA.O_AGR_NO;
        CMCS8840.AC_TYP2 = 'X';
        CMCS8840.RMK2 = WS_CR_MMO;
        CMCS8840.CCY_NO2 = DDCSTRAC.TO_CCY;
        CMCS8840.CCY_TYP2 = DDCSTRAC.TO_CCY_TYPE;
        CMCS8840.AMT = DDCSTRAC.FR_AMT;
        CMCS8840.VAL_DT = DDCSTRAC.VAL_DATE;
        CMCS8840.HLD_NO = DCCUHLD.DATA.HLD_NO;
        CMCSELAY.TRIG_DATA = IBS.CLS2CPY(SCCGWA, CMCS8840);
        CEP.TRC(SCCGWA, CMCSELAY.TRF_TYP);
        CEP.TRC(SCCGWA, CMCSELAY.HLD_NO);
        S000_CALL_CMZSELAY();
    }
    public void B010_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CARD);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.EX_RATE);
        CEP.TRC(SCCGWA, DDCSTRAC.TICK_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.EX_TIME);
        CEP.TRC(SCCGWA, DDCSTRAC.VAL_DATE);
        CEP.TRC(SCCGWA, DDCSTRAC.TX_RMK);
        CEP.TRC(SCCGWA, DDCSTRAC.DR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.CR_MMO);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && DDCSTRAC.VAL_DATE > GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NOT_ALLOW_REVERSE;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 27);
        }
        if (DDCSTRAC.FR_AC.trim().length() == 0 
            && DDCSTRAC.FR_CARD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 4);
        }
        if (DDCSTRAC.FR_BV_TYPE == '3') {
            R000_GET_AICPQMIB_FR();
            if (AICPQMIB.RC.RC_CODE == 8917 
                || AICPQMIB.RC.RC_CODE == 8924) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FR_MST_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCSTRAC.TO_BV_TYPE == '3') {
            R000_GET_AICPQMIB_TO();
            if (AICPQMIB.RC.RC_CODE == 8917 
                || AICPQMIB.RC.RC_CODE == 8924) {
                CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
                CEP.TRC(SCCGWA, "22222222222222222");
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TO_MST_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        WS_SAVE_STRAC_FR_AC = DDCSTRAC.FR_AC;
        WS_SAVE_STRAC_TO_AC = DDCSTRAC.TO_AC;
        if (DDCSTRAC.FR_CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 5);
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (DDCSTRAC.FR_AMT == 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("0115846")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TRF_AMT_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 13);
        } else {
            if (DDCSTRAC.FR_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                CEP.ERR(SCCGWA, WS_ERR_MSG, 13);
            }
        }
        if (DDCSTRAC.TO_AC.trim().length() == 0 
            && DDCSTRAC.TO_CARD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 17);
        }
        if (DDCSTRAC.TO_AC.equalsIgnoreCase(DDCSTRAC.FR_AC) 
            && DDCSTRAC.FR_CCY.equalsIgnoreCase(DDCSTRAC.TO_CCY) 
            && DDCSTRAC.TO_CCY_TYPE == DDCSTRAC.FR_CCY_TYPE 
            && DDCSTRAC.FR_AC.trim().length() > 0 
            && DDCSTRAC.TO_AC.trim().length() > 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CAN_NOT_SAME;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 17);
        }
        if (DDCSTRAC.CHQ_TYPE == '1' 
            || DDCSTRAC.CHQ_TYPE == '2' 
            || DDCSTRAC.CHQ_TYPE == '3' 
            || DDCSTRAC.CHQ_TYPE == '4' 
            || DDCSTRAC.CHQ_TYPE == '5') {
            if (DDCSTRAC.CHQ_NO.equalsIgnoreCase("0")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_MUST_INP;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDCSTRAC.CHQ_NO.trim().length() > 0 
            && DDCSTRAC.VAL_DATE != 0 
            && DDCSTRAC.VAL_DATE < DDCSTRAC.CHQ_ISS_DATE 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_DT_M_LG_ISDT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 27);
        }
        CEP.TRC(SCCGWA, DDCSTRAC.VAL_DATE);
        if (DDCSTRAC.VAL_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSTRAC.VAL_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_DT_INVALID;
                CEP.ERR(SCCGWA, WS_ERR_MSG, 27);
            }
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCSTRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            } else {
                DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if ((DDCSTRAC.FR_AMT == 0) 
            && (DDCSTRAC.TO_AMT == 0) 
            && !JIBS_tmp_str[0].equalsIgnoreCase("0115846")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG, 13);
        }
        if ((DDCSTRAC.TO_CCY.trim().length() > 0) 
            && (!DDCSTRAC.TO_CCY.equalsIgnoreCase(DDCSTRAC.FR_CCY))) {
            if ((DDCSTRAC.TO_AMT != 0) 
                && (DDCSTRAC.FR_AMT == 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG, 13);
            }
            if ((DDCSTRAC.FR_AMT != 0) 
                && (DDCSTRAC.TO_AMT == 0)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG, 23);
            }
        }
        if (DDCSTRAC.TO_CCY.equalsIgnoreCase(DDCSTRAC.FR_CCY)) {
            if ((DDCSTRAC.TO_AMT != 0) 
                && (DDCSTRAC.FR_AMT == 0)) {
                DDCSTRAC.FR_AMT = DDCSTRAC.TO_AMT;
            }
            if ((DDCSTRAC.FR_AMT != 0) 
                && (DDCSTRAC.TO_AMT == 0)) {
                DDCSTRAC.TO_AMT = DDCSTRAC.FR_AMT;
            }
        }
        if (DDCSTRAC.FR_CCY.trim().length() > 0 
            && DDCSTRAC.TO_CCY.trim().length() > 0 
            && !DDCSTRAC.FR_CCY.equalsIgnoreCase(DDCSTRAC.TO_CCY)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_NOT_SAME);
        }
        if (DDCSTRAC.DR_MMO.trim().length() == 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                WS_DR_MMO = "G004";
            } else {
                WS_DR_MMO = "A019";
            }
        } else {
            WS_DR_MMO = DDCSTRAC.DR_MMO;
        }
        CEP.TRC(SCCGWA, WS_DR_MMO);
        if (DDCSTRAC.CR_MMO.trim().length() == 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                WS_CR_MMO = "G004";
            } else {
                WS_CR_MMO = "A001";
            }
        } else {
            WS_CR_MMO = DDCSTRAC.CR_MMO;
        }
        CEP.TRC(SCCGWA, WS_CR_MMO);
    }
    public void B014_GET_STD_AC_INFO() throws IOException,SQLException,Exception {
        WS_AC_ROUTINE_FLG = 'N';
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CARD);
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICFACRI);
        CICQACRI.FUNC = 'A';
        if (DDCSTRAC.FR_AC.trim().length() > 0) {
            CICQACRI.DATA.AGR_NO = DDCSTRAC.FR_AC;
        } else {
            CICQACRI.DATA.AGR_NO = DDCSTRAC.FR_CARD;
        }
        S000_CALL_CIZQACRI();
        IBS.CLONE(SCCGWA, CICQACRI, CICFACRI);
        DDCSTRAC.O_DATA.CI_NO = CICFACRI.O_DATA.O_CI_NO;
        DDCSTRAC.O_DATA.CHG_AC = CICFACRI.O_DATA.O_AGR_NO;
        WS_FR_CI_TYPE = CICFACRI.O_DATA.O_CI_TYP;
        WS_FR_OWNER_BR = CICFACRI.O_DATA.O_OPN_BR;
        WS_AC_ROUTINE_FLG = 'N';
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CARD);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        if (DDCSTRAC.TO_AC.trim().length() > 0) {
            CICQACRI.DATA.AGR_NO = DDCSTRAC.TO_AC;
        } else {
            CICQACRI.DATA.AGR_NO = DDCSTRAC.TO_CARD;
        }
        S000_CALL_CIZQACRI();
        WS_TO_OWNER_BR = CICQACRI.O_DATA.O_OPN_BR;
        WS_TO_CI_TYPE = CICQACRI.O_DATA.O_CI_TYP;
        CEP.TRC(SCCGWA, WS_FR_CI_TYPE);
        CEP.TRC(SCCGWA, WS_TO_CI_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY_TYPE);
        if (DDCSTRAC.FR_CCY_TYPE != DDCSTRAC.TO_CCY_TYPE) {
            B010_CHECK_CCY_TYPE_NOT_SAME();
        }
    }
    public void B015_GET_IND_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        WS_PQORG_TRA_TYP = BPCPQORG.TRA_TYP;
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_FRM_APP);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICFACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_AGR_NO);
            DDRMST.KEY.CUS_AC = CICFACRI.O_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            WS_FR_FRG_IND = DDRMST.FRG_IND;
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
            DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            WS_TO_FRG_IND = DDRMST.FRG_IND;
        }
        CEP.TRC(SCCGWA, WS_FR_FRG_IND);
        CEP.TRC(SCCGWA, WS_TO_FRG_IND);
        CEP.TRC(SCCGWA, WS_FR_OWNER_BR);
        CEP.TRC(SCCGWA, WS_TO_OWNER_BR);
        if (WS_FR_OWNER_BR != 0 
            && WS_TO_OWNER_BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = WS_FR_OWNER_BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
            WS_FR_TRA_TYP = BPCPQORG.TRA_TYP;
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = WS_TO_OWNER_BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
            WS_TO_TRA_TYP = BPCPQORG.TRA_TYP;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0111100") 
            && (!WS_FR_TRA_TYP.equalsIgnoreCase(WS_TO_TRA_TYP))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTAC_TRANS_CANT_DO);
        }
        if (!WS_PQORG_TRA_TYP.equalsIgnoreCase("00") 
            && (WS_FR_TRA_TYP.equalsIgnoreCase("00") 
            || WS_TO_TRA_TYP.equalsIgnoreCase("00"))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ONLY_FTA_AC_TXN_FTA);
        }
        if (WS_PQORG_TRA_TYP.equalsIgnoreCase("00") 
            && (!WS_FR_TRA_TYP.equalsIgnoreCase("00") 
            || !WS_TO_TRA_TYP.equalsIgnoreCase("00"))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ONLY_FTA_AC_TXN_FTA);
        }
        if (!WS_FR_TRA_TYP.equalsIgnoreCase(WS_PQORG_TRA_TYP) 
            || !WS_TO_TRA_TYP.equalsIgnoreCase(WS_PQORG_TRA_TYP)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FTAC_TRANS_CANT_DO);
        }
    }
    public void B013_GET_STD_AC_INFO() throws IOException,SQLException,Exception {
        WS_FR_AC = DDCSTRAC.FR_AC;
        WS_TO_AC = DDCSTRAC.TO_AC;
        if (DDCSTRAC.FR_CARD.trim().length() > 0 
            && DDCSTRAC.FR_BV_TYPE == '1') {
            IBS.init(SCCGWA, DCCPACTY);
            IBS.init(SCCGWA, DDRMST);
            DCCPACTY.INPUT.FUNC = '1';
            DCCPACTY.INPUT.AC = DDCSTRAC.FR_CARD;
            DCCPACTY.INPUT.CCY = DDCSTRAC.FR_CCY;
            DCCPACTY.INPUT.CCY_TYPE = DDCSTRAC.FR_CCY_TYPE;
            S000_CALL_DCZPACTY();
            WS_FR_AC = DCCPACTY.OUTPUT.STD_AC;
            if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
            JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
            if (DDRMST.AC_STS_WORD.substring(41 - 1, 41 + 1 - 1).equalsIgnoreCase("1")) {
                WS_SOCIAL_CARD_FLG = 'Y';
            }
            if (DCCPACTY.OUTPUT.SASB_AC_FLG == 'Y') {
                WS_N_FR_CARD = DCCPACTY.OUTPUT.SASB_CARD_NO;
            }
            if (DCCPACTY.OUTPUT.CARD_O_N_FLG == 'O') {
                WS_N_FR_CARD = DCCPACTY.OUTPUT.N_CARD_NO;
            }
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.CARD_O_N_FLG);
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.SASB_AC_FLG);
            CEP.TRC(SCCGWA, WS_N_FR_CARD);
        }
        if (DDCSTRAC.TO_CARD.trim().length() > 0 
            && DDCSTRAC.TO_BV_TYPE == '1') {
            IBS.init(SCCGWA, DCCPACTY);
            DCCPACTY.INPUT.FUNC = '1';
            DCCPACTY.INPUT.AC = DDCSTRAC.TO_CARD;
            DCCPACTY.INPUT.CCY = DDCSTRAC.TO_CCY;
            DCCPACTY.INPUT.CCY_TYPE = DDCSTRAC.TO_CCY_TYPE;
            S000_CALL_DCZPACTY();
            WS_TO_AC = DCCPACTY.OUTPUT.STD_AC;
            if (DCCPACTY.OUTPUT.SASB_AC_FLG == 'Y') {
                WS_N_TO_CARD = DCCPACTY.OUTPUT.SASB_CARD_NO;
            }
            if (DCCPACTY.OUTPUT.CARD_O_N_FLG == 'O') {
                WS_N_TO_CARD = DCCPACTY.OUTPUT.N_CARD_NO;
            }
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.CARD_O_N_FLG);
            CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.SASB_AC_FLG);
            CEP.TRC(SCCGWA, WS_N_TO_CARD);
        }
    }
    public void B015_CHK_SAME_NAME() throws IOException,SQLException,Exception {
    }
    public void B030_EXG_AMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY);
        if (!DDCSTRAC.TO_CCY.equalsIgnoreCase(DDCSTRAC.FR_CCY)) {
            WS_BUY_CCY = DDCSTRAC.FR_CCY;
            WS_BUY_CCY_TYPE = DDCSTRAC.FR_CCY_TYPE;
            WS_BUY_AMT = DDCSTRAC.FR_AMT;
            WS_SELL_CCY = DDCSTRAC.TO_CCY;
            WS_SELL_CCY_TYPE = DDCSTRAC.TO_CCY_TYPE;
            WS_SELL_AMT = DDCSTRAC.TO_AMT;
        }
    }
    public void B035_CARD_LIMT_BAL_CHK() throws IOException,SQLException,Exception {
        if (DDCSTRAC.FR_BV_TYPE == '1' 
            && DDCSTRAC.FR_CARD.trim().length() > 0) {
            IBS.init(SCCGWA, DCCPFTCK);
            if (WS_N_FR_CARD.trim().length() > 0) {
                DCCPFTCK.VAL.CARD_NO = WS_N_FR_CARD;
            } else {
                DCCPFTCK.VAL.CARD_NO = DDCSTRAC.FR_CARD;
            }
            DCCPFTCK.VAL.REGN_TYP = '0';
            DCCPFTCK.VAL.TXN_TYPE = "04";
            DCCPFTCK.VAL.TXN_CCY = DDCSTRAC.FR_CCY;
            DCCPFTCK.VAL.TXN_AMT = DDCSTRAC.FR_AMT;
            DCCPFTCK.TRK2_DAT = DDCSTRAC.TRK2_DAT;
            DCCPFTCK.TRK3_DAT = DDCSTRAC.TRK3_DAT;
            if (WS_SAME_NAME_FLG == 'Y') {
                DCCPFTCK.FUNCTION_CODE = 'S';
            } else {
                DCCPFTCK.FUNCTION_CODE = ' ';
            }
            CEP.TRC(SCCGWA, DCCPFTCK.TRK2_DAT);
            CEP.TRC(SCCGWA, DCCPFTCK.TRK3_DAT);
            S000_CALL_DCZPFTCK();
        }
    }
    public void B037_RELS_PAY_PRCO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUUPAY);
        DDCUUPAY.FUNC = '7';
        if (DDCSTRAC.FR_CARD.trim().length() > 0) {
            DDCUUPAY.DD_AC = DDCSTRAC.FR_CARD;
        } else {
            DDCUUPAY.DD_AC = DDCSTRAC.FR_AC;
        }
        DDCUUPAY.CCY = DDCSTRAC.FR_CCY;
        DDCUUPAY.CCY_TYP = DDCSTRAC.FR_CCY_TYPE;
        DDCUUPAY.ACMT_AMT = DDCSTRAC.FR_AMT;
        DDCUUPAY.OTH_NAME = DDCSTRAC.TO_AC_CNAME;
        S000_CALL_DDZUUPAY();
    }
    public void B040_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        CEP.TRC(SCCGWA, DDCSTRAC.CHK_PSW_FLG);
        if (DDCSTRAC.CHK_PSW_FLG == ' ') {
            DDCUDRAC.CHK_PSW_FLG = 'Y';
        } else {
            DDCUDRAC.CHK_PSW_FLG = DDCSTRAC.CHK_PSW_FLG;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CHK_PSW_FLG);
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            DDCUDRAC.CHK_PSW_FLG = 'N';
        }
        DDCUDRAC.BV_TYP = DDCSTRAC.FR_BV_TYPE;
        DDCUDRAC.AC = CICFACRI.O_DATA.O_AGR_NO;
        DDCUDRAC.CCY = DDCSTRAC.FR_CCY;
        DDCUDRAC.CCY_TYPE = DDCSTRAC.FR_CCY_TYPE;
        DDCUDRAC.TX_AMT = DDCSTRAC.FR_AMT;
        DDCUDRAC.CARD_NO = DDCSTRAC.FR_CARD;
        DDCUDRAC.PSBK_NO = DDCSTRAC.FR_PSBK;
        if (DDCSTRAC.TO_AC.trim().length() > 0) {
            DDCUDRAC.OTHER_AC = DDCSTRAC.TO_AC;
        } else {
            DDCUDRAC.OTHER_AC = DDCSTRAC.TO_CARD;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.OTHER_AC);
        DDCUDRAC.VAL_DATE = DDCSTRAC.VAL_DATE;
        DDCUDRAC.NARRATIVE = DDCSTRAC.TX_RMK;
        DDCUDRAC.REMARKS = DDCSTRAC.REMARKS;
        DDCUDRAC.PSWD = DDCSTRAC.PSWD;
        if (DDCSTRAC.FR_BV_TYPE != '3' 
            && DDCSTRAC.TO_BV_TYPE == '3') {
            DDCUDRAC.RLT_AC = DDCSTRAC.RLT_AC;
            DDCUDRAC.RLT_AC_NAME = DDCSTRAC.RLT_AC_NAME;
            DDCUDRAC.RLT_BANK = DDCSTRAC.RLT_BANK;
            DDCUDRAC.RLT_BK_NM = DDCSTRAC.RLT_BK_NM;
            DDCUDRAC.RLT_REF_NO = DDCSTRAC.RLT_REF_NO;
            DDCUDRAC.RLT_CCY = DDCSTRAC.RLT_CCY;
            DDCUDRAC.RLT_CI_TYP = DDCSTRAC.RLT_CI_TYP;
        }
        CEP.TRC(SCCGWA, DDCSTRAC.RLT_CI_TYP);
        CEP.TRC(SCCGWA, DDCUDRAC.RLT_AC);
        CEP.TRC(SCCGWA, DDCUDRAC.RLT_AC_NAME);
        CEP.TRC(SCCGWA, DDCUDRAC.RLT_BANK);
        CEP.TRC(SCCGWA, DDCUDRAC.RLT_BK_NM);
        CEP.TRC(SCCGWA, DDCSTRAC.DR_MMO);
        CEP.TRC(SCCGWA, DDCSTRAC.CR_MMO);
        if (DDCSTRAC.DR_MMO.trim().length() == 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCUDRAC.TX_MMO = "G004";
            } else {
                DDCUDRAC.TX_MMO = "A019";
            }
        } else {
            DDCUDRAC.TX_MMO = DDCSTRAC.DR_MMO;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.TX_MMO);
        DDCUDRAC.PAY_TYPE = DDCSTRAC.PAY_TYPE;
        DDCUDRAC.CHQ_TYPE = DDCSTRAC.CHQ_TYPE;
        DDCUDRAC.CHQ_NO = DDCSTRAC.CHQ_NO;
        DDCUDRAC.CHQ_ISS_DATE = DDCSTRAC.CHQ_ISS_DATE;
        DDCUDRAC.PAY_PSWD = DDCSTRAC.PAY_PSWD;
        CEP.TRC(SCCGWA, DDCUDRAC.PAY_PSWD);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CARD);
        DDCUDRAC.OTH_TX_TOOL = DDCSTRAC.TO_CARD;
        CEP.TRC(SCCGWA, DDCSTRAC.TX_BAL_FLG);
        DDCUDRAC.TX_BAL_FLG = DDCSTRAC.TX_BAL_FLG;
        DDCUDRAC.PSBK_SEQ = DDCSTRAC.FR_PSBK_SEQ;
        CEP.TRC(SCCGWA, DDCUDRAC.PSBK_SEQ);
        DDCUDRAC.CHK_PSW = DDCSTRAC.CHK_PSW;
        DDCUDRAC.TRK_DATE2 = DDCSTRAC.TRK2_DAT;
        DDCUDRAC.TRK_DATE3 = DDCSTRAC.TRK3_DAT;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("181810") 
            || JIBS_tmp_str[0].equalsIgnoreCase("181200") 
            || JIBS_tmp_str[0].equalsIgnoreCase("181300") 
            || JIBS_tmp_str[0].equalsIgnoreCase("183200") 
            || JIBS_tmp_str[0].equalsIgnoreCase("181450")) {
            DDCUDRAC.GD_WITHDR_FLG = 'Y';
        }
        DDCUDRAC.TXN_REGION = DDCSTRAC.TXN_REGION;
        DDCUDRAC.TXN_CHNL = DDCSTRAC.TXN_CHNL;
        DDCUDRAC.TXN_TYPE = DDCSTRAC.TXN_TYPE;
        DDCUDRAC.IN_OUT_FLG = DDCSTRAC.IN_OUT_FLG;
        DDCUDRAC.SNAME_FLG = DDCSTRAC.SNAME_FLG;
        DDCUDRAC.DNAME_FLG = DDCSTRAC.DNAME_FLG;
        DDCUDRAC.CANCEL_FLG = DDCSTRAC.CANCEL_FLG;
        DDCUDRAC.CHK_LMT_FLG = DDCSTRAC.CHK_LMT_FLG;
        S000_CALL_DDZUDRAC();
        if (DDCSTRAC.TX_BAL_FLG == 'Y') {
            if (DDCUDRAC.TX_AMT == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_BAL_ZERO;
                S000_ERR_MSG_PROC();
            } else {
                DDCSTRAC.FR_AMT = DDCUDRAC.TX_AMT;
                DDCSTRAC.TO_AMT = DDCSTRAC.FR_AMT;
            }
        }
    }
    public void B041_CALL_AI_DR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = CICFACRI.O_DATA.O_AGR_NO;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = DDCSTRAC.FR_AMT;
        AICUUPIA.DATA.CCY = DDCSTRAC.FR_CCY;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = DDCSTRAC.FR_CREV_NO;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.THEIR_AC = CICQACRI.O_DATA.O_AGR_NO;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = DDCSTRAC.TO_CCY;
        AICUUPIA.DATA.THEIR_AMT = DDCSTRAC.TO_AMT;
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC_CNAME);
        AICUUPIA.DATA.PAY_MAN = DDCSTRAC.TO_AC_CNAME;
        AICUUPIA.DATA.PAY_BR = CICQACRI.O_DATA.O_OPN_BR;
        AICUUPIA.DATA.DESC = DDCSTRAC.REMARKS;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.DESC);
        S000_CALL_AIZUUPIA();
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B051_CALL_AI_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = CICQACRI.O_DATA.O_AGR_NO;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = DDCSTRAC.TO_AMT;
        AICUUPIA.DATA.CCY = DDCSTRAC.TO_CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = DDCSTRAC.TO_CREV_NO;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.THEIR_AC = CICFACRI.O_DATA.O_AGR_NO;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = DDCSTRAC.FR_CCY;
        AICUUPIA.DATA.THEIR_AMT = DDCSTRAC.FR_AMT;
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC_CNAME);
        AICUUPIA.DATA.PAY_MAN = DDCSTRAC.FR_AC_CNAME;
        AICUUPIA.DATA.PAY_BR = CICFACRI.O_DATA.O_OPN_BR;
        AICUUPIA.DATA.DESC = DDCSTRAC.REMARKS;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.DESC);
        S000_CALL_AIZUUPIA();
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        CEP.TRC(SCCGWA, AICUUPIA.RC.RC_CODE);
        CEP.TRC(SCCGWA, "XIANGUANYAO");
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
        } else {
            CEP.TRC(SCCGWA, "XIANGUANYAO55555");
            DDCSTRAC.TO_CREV_NO = AICUUPIA.DATA.RVS_NO;
        }
    }
    public void B050_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC);
        CEP.TRC(SCCGWA, WS_SAVE_STRAC_TO_AC);
        DDCUCRAC.BV_TYP = DDCSTRAC.TO_BV_TYPE;
        DDCUCRAC.AC = CICQACRI.O_DATA.O_AGR_NO;
        DDCUCRAC.CCY = DDCSTRAC.TO_CCY;
        DDCUCRAC.CCY_TYPE = DDCSTRAC.TO_CCY_TYPE;
        DDCUCRAC.TX_AMT = DDCSTRAC.TO_AMT;
        DDCUCRAC.CARD_NO = DDCSTRAC.TO_CARD;
        if (DDCSTRAC.FR_AC.trim().length() > 0) {
            DDCUCRAC.OTHER_AC = DDCSTRAC.FR_AC;
        } else {
            DDCUCRAC.OTHER_AC = DDCSTRAC.FR_CARD;
        }
        DDCUCRAC.VAL_DATE = DDCSTRAC.VAL_DATE;
        DDCUCRAC.NARRATIVE = DDCSTRAC.TX_RMK;
        DDCUCRAC.REMARKS = DDCSTRAC.REMARKS;
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CARD);
        DDCUCRAC.OTH_TX_TOOL = DDCSTRAC.FR_CARD;
        CEP.TRC(SCCGWA, DDCSTRAC.CR_MMO);
        DDCUCRAC.PSBK_SEQ = DDCSTRAC.TO_PSBK_SEQ;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("0353030") 
            || JIBS_tmp_str[0].equalsIgnoreCase("0353020") 
            || JIBS_tmp_str[0].equalsIgnoreCase("0353090") 
            || (DDCSTRAC.FR_BV_TYPE == '3' 
            && DDCSTRAC.TO_BV_TYPE != '3')) {
            DDCUCRAC.RLT_AC = DDCSTRAC.RLT_AC;
            DDCUCRAC.RLT_AC_NAME = DDCSTRAC.RLT_AC_NAME;
            DDCUCRAC.RLT_BANK = DDCSTRAC.RLT_BANK;
            DDCUCRAC.RLT_BK_NM = DDCSTRAC.RLT_BK_NM;
            DDCUCRAC.RLT_CI_TYP = DDCSTRAC.RLT_CI_TYP;
        }
        CEP.TRC(SCCGWA, DDCUCRAC.RLT_AC);
        CEP.TRC(SCCGWA, DDCUCRAC.PSBK_SEQ);
        if (DDCSTRAC.CR_MMO.trim().length() == 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                DDCUCRAC.TX_MMO = "G004";
            } else {
                DDCUCRAC.TX_MMO = "A001";
            }
        } else {
            DDCUCRAC.TX_MMO = DDCSTRAC.CR_MMO;
        }
        CEP.TRC(SCCGWA, DDCUCRAC.TX_MMO);
        S000_CALL_DDZUCRAC();
    }
    public void B060_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSTRAC.FR_BV_AM);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CARD);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_PSBK);
        CEP.TRC(SCCGWA, DDCSTRAC.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.CHQ_ISS_DATE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC_CNAME);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AC_ENAME);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_BV_AM);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_BV_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CARD);
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CREV_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CREV_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC_CNAME);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AC_ENAME);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_AMT);
        CEP.TRC(SCCGWA, DDCSTRAC.EX_RATE);
        CEP.TRC(SCCGWA, DDCSTRAC.TICK_NO);
        CEP.TRC(SCCGWA, DDCSTRAC.EX_TIME);
        CEP.TRC(SCCGWA, DDCSTRAC.VAL_DATE);
        CEP.TRC(SCCGWA, DDCSTRAC.TX_RMK);
        CEP.TRC(SCCGWA, DDCSTRAC.REMARKS);
        CEP.TRC(SCCGWA, DDCSTRAC.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCSTRAC.PSWD);
        CEP.TRC(SCCGWA, DDCSTRAC.PAY_PSWD);
        IBS.init(SCCGWA, DDCOTRAC);
        DDCOTRAC.FR_BV_AMT = DDCSTRAC.FR_BV_AM;
        DDCOTRAC.FR_BV_TYPE = DDCSTRAC.FR_BV_TYPE;
        DDCOTRAC.FR_AC = WS_SAVE_STRAC_FR_AC;
        DDCOTRAC.ENG_NM1 = DDCSTRAC.FR_AC_ENAME;
        DDCOTRAC.CHN_NM1 = DDCSTRAC.FR_AC_CNAME;
        DDCOTRAC.TO_BV_AMT = DDCSTRAC.TO_BV_AM;
        DDCOTRAC.TO_BV_TYPE = DDCSTRAC.TO_BV_TYPE;
        DDCOTRAC.TO_AC = WS_SAVE_STRAC_TO_AC;
        DDCOTRAC.ENG_NM2 = DDCSTRAC.TO_AC_ENAME;
        DDCOTRAC.CHN_NM2 = DDCSTRAC.TO_AC_CNAME;
        DDCOTRAC.FR_CCY = DDCSTRAC.FR_CCY;
        DDCOTRAC.FR_CCY_TYPE = DDCSTRAC.FR_CCY_TYPE;
        DDCOTRAC.TO_CCY = DDCSTRAC.TO_CCY;
        DDCOTRAC.TO_CCY_TYPE = DDCSTRAC.TO_CCY_TYPE;
        DDCOTRAC.FR_AMT = DDCSTRAC.FR_AMT;
        DDCOTRAC.TO_AMT = DDCSTRAC.TO_AMT;
        DDCOTRAC.CHQ_TYPE = DDCSTRAC.CHQ_TYPE;
        DDCOTRAC.ISS_DATE = DDCSTRAC.CHQ_ISS_DATE;
        if (!DDCSTRAC.CHQ_NO.equalsIgnoreCase("0")) {
            DDCOTRAC.CHQ_NO = DDCSTRAC.CHQ_NO;
        }
        DDCOTRAC.FR_PSBK = DDCSTRAC.FR_PSBK;
        DDCOTRAC.FR_CARD = DDCSTRAC.FR_CARD;
        DDCOTRAC.FR_CREV = DDCSTRAC.FR_CREV_NO;
        DDCOTRAC.TO_CREV = DDCSTRAC.TO_CREV_NO;
        DDCOTRAC.TO_CARD = DDCSTRAC.TO_CARD;
        DDCOTRAC.VAL_DATE = DDCSTRAC.VAL_DATE;
        DDCOTRAC.TX_REF = DDCSTRAC.TX_RMK;
        DDCOTRAC.TICKET_NO = DDCSTRAC.TICK_NO;
        DDCOTRAC.EX_RATE = DDCSTRAC.EX_RATE;
        DDCOTRAC.EX_TM = DDCSTRAC.EX_TIME;
        DDCOTRAC.RMKS = DDCSTRAC.REMARKS;
        DDCOTRAC.PAY_TYPE = DDCSTRAC.PAY_TYPE;
        DDCOTRAC.PSWD = DDCSTRAC.PSWD;
        DDCOTRAC.PAY_PSWD = DDCSTRAC.PAY_PSWD;
        DDCOTRAC.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
        CEP.TRC(SCCGWA, DDCOTRAC.TX_MMO);
        CEP.TRC(SCCGWA, DDCOTRAC.ENG_NM2);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_BV_AMT);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_BV_TYPE);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_CARD);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_AC);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_CCY);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCOTRAC.CHN_NM1);
        CEP.TRC(SCCGWA, DDCOTRAC.ENG_NM1);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_PSBK);
        CEP.TRC(SCCGWA, DDCOTRAC.CHQ_TYPE);
        CEP.TRC(SCCGWA, DDCOTRAC.CHQ_NO);
        CEP.TRC(SCCGWA, DDCOTRAC.ISS_DATE);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_AMT);
        CEP.TRC(SCCGWA, DDCOTRAC.TO_BV_AMT);
        CEP.TRC(SCCGWA, DDCOTRAC.TO_BV_TYPE);
        CEP.TRC(SCCGWA, DDCOTRAC.TO_CARD);
        CEP.TRC(SCCGWA, DDCOTRAC.TO_AC);
        CEP.TRC(SCCGWA, DDCOTRAC.TO_CCY);
        CEP.TRC(SCCGWA, DDCOTRAC.TO_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCOTRAC.FR_CREV);
        CEP.TRC(SCCGWA, DDCOTRAC.TO_CREV);
        CEP.TRC(SCCGWA, DDCOTRAC.PAY_TYPE);
        CEP.TRC(SCCGWA, DDCOTRAC.PSWD);
        CEP.TRC(SCCGWA, DDCOTRAC.PAY_PSWD);
        CEP.TRC(SCCGWA, DDCOTRAC.CHN_NM2);
        CEP.TRC(SCCGWA, DDCOTRAC.ENG_NM2);
        CEP.TRC(SCCGWA, DDCOTRAC.TO_AMT);
        CEP.TRC(SCCGWA, DDCOTRAC.EX_RATE);
        CEP.TRC(SCCGWA, DDCOTRAC.TICKET_NO);
        CEP.TRC(SCCGWA, DDCOTRAC.EX_TM);
        CEP.TRC(SCCGWA, DDCOTRAC.VAL_DATE);
        CEP.TRC(SCCGWA, DDCOTRAC.TX_REF);
        CEP.TRC(SCCGWA, DDCOTRAC.RMKS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOTRAC;
        SCCFMT.DATA_LEN = 1616;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICFACRI.O_DATA.O_CI_NO;
        CICSAGEN.OUT_AC = CICFACRI.O_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void B070_AGENT_INF_PORC_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICFACRI.O_DATA.O_CI_NO;
        CICSAGEN.OUT_AC = CICFACRI.O_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = DDCSTRAC.AGE_ID_TYPE;
        CICSAGEN.ID_NO = DDCSTRAC.AGE_ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = DDCSTRAC.AGE_CI_NM;
        CICSAGEN.CNTY = DDCSTRAC.AGE_CNTY;
        S000_CALL_CIZSAGEN();
    }
    public void B100_FOREIGN_CURR_PROC() throws IOException,SQLException,Exception {
        if (WS_FR_CI_TYPE == '2' 
            && WS_TO_CI_TYPE == '2') {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICFACRI.O_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            WS_FR_AC_TYPE = DDRMST.AC_TYPE;
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = CICQACRI.O_DATA.O_AGR_NO;
            T000_READ_DDTMST();
            WS_TO_AC_TYPE = DDRMST.AC_TYPE;
        }
        CEP.TRC(SCCGWA, DDCSTRAC.FR_CCY);
        CEP.TRC(SCCGWA, DDCSTRAC.TO_CCY);
        CEP.TRC(SCCGWA, WS_FR_AC_TYPE);
        CEP.TRC(SCCGWA, WS_TO_AC_TYPE);
        if (!DDCSTRAC.FR_CCY.equalsIgnoreCase("156") 
            && !DDCSTRAC.TO_CCY.equalsIgnoreCase("156") 
            && DDCSTRAC.FR_CCY.equalsIgnoreCase(DDCSTRAC.TO_CCY) 
            && WS_FR_AC_TYPE != ' ' 
            && WS_TO_AC_TYPE != ' ') {
            if (WS_FR_AC_TYPE == WS_TO_AC_TYPE) {
                CEP.TRC(SCCGWA, "CONTINUE");
            } else {
                if ((WS_FR_AC_TYPE == 'G' 
                    && WS_TO_AC_TYPE == 'H') 
                    || (WS_FR_AC_TYPE == 'H' 
                    && WS_TO_AC_TYPE == 'G')) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYPE_NO_SAME_AUTH;
                    S000_ERR_MSG_PROC();
                } else {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_TYPE_NO_SAME_CNOT;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void B080_INTER_REP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMREG);
        DDCSMREG.FUNC = 'A';
        DDCSMREG.REP_TYP = DDCSTRAC.REP_TYP;
        DDCSMREG.BASE_FLG = DDCSTRAC.BASE_FLG;
        DDCSMREG.REP_FLG = DDCSTRAC.REP_FLG;
        DDCSMREG.MAN_FLG = DDCSTRAC.MAN_FLG;
        DDCSMREG.TR_AC = DDCSTRAC.TR_AC;
        DDCSMREG.CI_TYP = DDCSTRAC.CI_TYP;
        DDCSMREG.CI_ID_NO = DDCSTRAC.CI_ID_NO;
        DDCSMREG.ORG_CD = DDCSTRAC.ORG_CD;
        DDCSMREG.CI_NM = DDCSTRAC.CI_NM;
        DDCSMREG.TO_CI_NM = DDCSTRAC.TO_CI_NM;
        DDCSMREG.CCY = DDCSTRAC.CCY;
        DDCSMREG.TR_AMT = DDCSTRAC.TR_AMT;
        DDCSMREG.EX_RATE = DDCSTRAC.EX_RATE;
        DDCSMREG.EX_AMT = DDCSTRAC.EX_AMT;
        DDCSMREG.CHN_ACNO = DDCSTRAC.CHN_ACNO;
        DDCSMREG.CASH_AMT = DDCSTRAC.CASH_AMT;
        DDCSMREG.FOR_ACNO = DDCSTRAC.FOR_ACNO;
        DDCSMREG.OTH_AMT = DDCSTRAC.OTH_AMT;
        DDCSMREG.OTH_ACNO = DDCSTRAC.OTH_ACNO;
        DDCSMREG.PAY_MTH = DDCSTRAC.PAY_MTH;
        DDCSMREG.CN_F_CCY = DDCSTRAC.CN_F_CCY;
        DDCSMREG.CN_F_AMT = DDCSTRAC.CN_F_AMT;
        DDCSMREG.FO_F_CCY = DDCSTRAC.FO_F_CCY;
        DDCSMREG.FO_F_AMT = DDCSTRAC.FO_F_AMT;
        DDCSMREG.CNTY_CD = DDCSTRAC.CNTY_CD;
        DDCSMREG.PAY_TYP = DDCSTRAC.PAY_TYP;
        DDCSMREG.TX_CODE1 = DDCSTRAC.TX_CODE1;
        DDCSMREG.AMT1 = DDCSTRAC.AMT1;
        DDCSMREG.REMARKS1 = DDCSTRAC.REMARKS1;
        DDCSMREG.TX_CODE2 = DDCSTRAC.TX_CODE2;
        DDCSMREG.AMT2 = DDCSTRAC.AMT2;
        DDCSMREG.REMARKS2 = DDCSTRAC.REMARKS2;
        DDCSMREG.REF_FLG = DDCSTRAC.REF_FLG;
        DDCSMREG.REF_NO = DDCSTRAC.REF_NO;
        DDCSMREG.PAY_ATTR = DDCSTRAC.REF_NO.charAt(0);
        DDCSMREG.REF_CKNO = DDCSTRAC.REF_CKNO;
        DDCSMREG.CHK_AMT = DDCSTRAC.CHK_AMT;
        DDCSMREG.IMP_DATE = DDCSTRAC.IMP_DATE;
        DDCSMREG.CONTR_NO = DDCSTRAC.CONTR_NO;
        DDCSMREG.INV_NO = DDCSTRAC.INV_NO;
        DDCSMREG.CUSM_CD = DDCSTRAC.CUSM_CD;
        DDCSMREG.CUSM_NO = DDCSTRAC.CUSM_NO;
        DDCSMREG.CUSM_CCY = DDCSTRAC.CUSM_CCY;
        DDCSMREG.CUSM_AMT = DDCSTRAC.CUSM_AMT;
        DDCSMREG.OFF_AMT = DDCSTRAC.OFF_AMT;
        DDCSMREG.CUSM_CNM = DDCSTRAC.CUSM_CNM;
        DDCSMREG.CUSM_TEL = DDCSTRAC.CUSM_TEL;
        DDCSMREG.CUSM_DT = DDCSTRAC.CUSM_DATE;
        CEP.TRC(SCCGWA, DDCSMREG.REP_TYP);
        CEP.TRC(SCCGWA, DDCSMREG.BASE_FLG);
        CEP.TRC(SCCGWA, DDCSMREG.REP_FLG);
        CEP.TRC(SCCGWA, DDCSMREG.MAN_FLG);
        CEP.TRC(SCCGWA, DDCSMREG.TR_AC);
        CEP.TRC(SCCGWA, DDCSMREG.CI_TYP);
        CEP.TRC(SCCGWA, DDCSMREG.CI_ID_NO);
        CEP.TRC(SCCGWA, DDCSMREG.ORG_CD);
        CEP.TRC(SCCGWA, DDCSMREG.CI_NM);
        CEP.TRC(SCCGWA, DDCSMREG.TO_CI_NM);
        CEP.TRC(SCCGWA, DDCSMREG.CCY);
        CEP.TRC(SCCGWA, DDCSMREG.TR_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.EX_RATE);
        CEP.TRC(SCCGWA, DDCSMREG.EX_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.CHN_ACNO);
        CEP.TRC(SCCGWA, DDCSMREG.CASH_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.FOR_ACNO);
        CEP.TRC(SCCGWA, DDCSMREG.OTH_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.OTH_ACNO);
        CEP.TRC(SCCGWA, DDCSMREG.PAY_MTH);
        CEP.TRC(SCCGWA, DDCSMREG.CN_F_CCY);
        CEP.TRC(SCCGWA, DDCSMREG.CN_F_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.FO_F_CCY);
        CEP.TRC(SCCGWA, DDCSMREG.FO_F_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.CNTY_CD);
        CEP.TRC(SCCGWA, DDCSMREG.PAY_TYP);
        CEP.TRC(SCCGWA, DDCSMREG.TX_CODE1);
        CEP.TRC(SCCGWA, DDCSMREG.AMT1);
        CEP.TRC(SCCGWA, DDCSMREG.REMARKS1);
        CEP.TRC(SCCGWA, DDCSMREG.TX_CODE2);
        CEP.TRC(SCCGWA, DDCSMREG.AMT2);
        CEP.TRC(SCCGWA, DDCSMREG.REMARKS2);
        CEP.TRC(SCCGWA, DDCSMREG.REF_FLG);
        CEP.TRC(SCCGWA, DDCSMREG.REF_NO);
        CEP.TRC(SCCGWA, DDCSMREG.PAY_ATTR);
        CEP.TRC(SCCGWA, DDCSMREG.REF_CKNO);
        CEP.TRC(SCCGWA, DDCSMREG.CHK_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.IMP_DATE);
        CEP.TRC(SCCGWA, DDCSMREG.CONTR_NO);
        CEP.TRC(SCCGWA, DDCSMREG.INV_NO);
        CEP.TRC(SCCGWA, DDCSMREG.CUSM_CD);
        CEP.TRC(SCCGWA, DDCSMREG.CUSM_NO);
        CEP.TRC(SCCGWA, DDCSMREG.CUSM_CCY);
        CEP.TRC(SCCGWA, DDCSMREG.CUSM_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.OFF_AMT);
        CEP.TRC(SCCGWA, DDCSMREG.CUSM_CNM);
        CEP.TRC(SCCGWA, DDCSMREG.CUSM_TEL);
        CEP.TRC(SCCGWA, DDCSMREG.CUSM_DT);
        S000_CALL_DDZSMREG();
    }
    public void B090_CCY_TYPE2_TYPE1_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FR_CI_TYPE);
        CEP.TRC(SCCGWA, WS_TO_CI_TYPE);
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        if (WS_FR_CI_TYPE == WS_TO_CI_TYPE 
            && (WS_FR_CI_TYPE == '1' 
            || WS_FR_CI_TYPE == '3')) {
            if (!CICFACRI.O_DATA.O_AGR_NO.equalsIgnoreCase(CICQACRI.O_DATA.O_AGR_NO)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FRAC_TOAC_M_SAME);
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TYPE2_TO_TYPE1_AUTH);
            }
        } else {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CYTP_NOSAME_M_PERSON);
        }
    }
    public void B090_CCY_TYPE1_TYPE2_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FR_CI_TYPE);
        CEP.TRC(SCCGWA, WS_TO_CI_TYPE);
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AGR_NO);
        if (WS_FR_CI_TYPE == WS_TO_CI_TYPE 
            && (WS_FR_CI_TYPE == '1' 
            || WS_FR_CI_TYPE == '3')) {
            if (!CICFACRI.O_DATA.O_AGR_NO.equalsIgnoreCase(CICQACRI.O_DATA.O_AGR_NO)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FRAC_TOAC_M_SAME);
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TYPE1_TO_TYPE2_AUTH);
            }
        } else {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CYTP_NOSAME_M_PERSON);
        }
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BUY_CCY);
        CEP.TRC(SCCGWA, WS_BUY_AMT);
        CEP.TRC(SCCGWA, WS_SELL_CCY);
        IBS.init(SCCGWA, BPCEX);
        BPCEX.BUY_CCY = WS_BUY_CCY;
        BPCEX.BUY_AMT = WS_BUY_AMT;
        BPCEX.SELL_CCY = WS_SELL_CCY;
        BPCEX.SELL_AMT = WS_SELL_AMT;
        if (DDCSTRAC.EX_RATE != 0) {
            BPCEX.TRN_EX_RATE = DDCSTRAC.EX_RATE;
        }
        S000_CALL_BPZSEX();
    }
    public void R000_GET_AICPQMIB_FR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = DDCSTRAC.FR_AC;
        AICPQMIB.INPUT_DATA.CCY = DDCSTRAC.FR_CCY;
        S000_CALL_AIZPQMIB();
    }
    public void R000_GET_AICPQMIB_TO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = DDCSTRAC.TO_AC;
        AICPQMIB.INPUT_DATA.CCY = DDCSTRAC.TO_CCY;
        S000_CALL_AIZPQMIB();
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_DDZUCRAC_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_DDZUCRAC_TS);
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_DDZUDRAC_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_DDZUDRAC_TS);
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_BPZSEX() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_BPZSEX_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_BPZSEX_TS);
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
    }
    public void S000_CALL_DDZUUPAY() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_DDZUUPAY_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_DDZUUPAY_TS);
        IBS.CALLCPN(SCCGWA, "DD-S-MOD-UPAY", DDCUUPAY);
    }
    public void S000_CALL_BPZFAMTA() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_BPZFAMTA_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_BPZFAMTA_TS);
        IBS.CALLCPN(SCCGWA, "BP-F-AMT-TBL-AUTH", BPCFAMTA);
        if (BPCFAMTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFAMTA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_DCZPFTCK_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_DCZPFTCK_TS);
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_AIZUUPIA_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_AIZUUPIA_TS);
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_AIZPIAEV() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_AIZPIAEV_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_AIZPIAEV_TS);
        IBS.CALLCPN(SCCGWA, "AI-P-IA-WRT-EWA", AICPIAEV);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_CIZQACRI_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_CIZQACRI_TS);
        if (WS_AC_ROUTINE_FLG == 'N') {
            IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
            if (CICQACRI.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, CICQACRI.RC);
            }
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_CIZQACRL_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_CIZQACRL_TS);
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRL.RC);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_CIZACCU_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_CIZACCU_TS);
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_AIZPQMIB_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_AIZPQMIB_TS);
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0 
            && AICPQMIB.RC.RC_CODE != 8917 
            && AICPQMIB.RC.RC_CODE != 8924) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_DCZPACTY_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_DCZPACTY_TS);
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_CIZQACAC_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, WS_CIZQACAC_TS);
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_DDZSMREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MOD-REG", DDCSMREG);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD);
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
    }
    public void S000_CALL_CMZSELAY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-SVR-CMZSELAY", CMCSELAY);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
