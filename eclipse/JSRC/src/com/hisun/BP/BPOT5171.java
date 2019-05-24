package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5171 {
    int JIBS_tmp_int;
    DBParm BPTTQP_RD;
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_INQ_TXN_QTP = "BP-INQ-TXN-QTP   ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    int WS_I = 0;
    String WS_CCY = " ";
    String WS_CORR_CCY = " ";
    int WS_UNIT = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT5171_WS_R_S_DT WS_R_S_DT = new BPOT5171_WS_R_S_DT();
    BPOT5171_WS_R_E_DT WS_R_E_DT = new BPOT5171_WS_R_E_DT();
    BPOT5171_WS_I_S_DT WS_I_S_DT = new BPOT5171_WS_I_S_DT();
    BPOT5171_WS_I_E_DT WS_I_E_DT = new BPOT5171_WS_I_E_DT();
    short WS_CNT = 0;
    BPOT5171_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPOT5171_WS_OUTPUT_DATA();
    char WS_OUT_REC_FLG = 'N';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCQQTP BPCQQTP = new BPCQQTP();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCRTQPH BPCRTQPH = new BPCRTQPH();
    SCCFMT SCCFMT = new SCCFMT();
    BPCF171 BPCF171 = new BPCF171();
    BPREXRT BPREXRT = new BPREXRT();
    BPREXRD BPREXRD = new BPREXRD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRTQP BPRTQP = new BPRTQP();
    BPRTQPH BPRTQPH = new BPRTQPH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPB5171_AWA_5171 BPB5171_AWA_5171;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5171 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5171_AWA_5171>");
        BPB5171_AWA_5171 = (BPB5171_AWA_5171) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5171_AWA_5171.EXR_TYP);
        CEP.TRC(SCCGWA, BPB5171_AWA_5171.CCY);
        CEP.TRC(SCCGWA, BPB5171_AWA_5171.BR);
        CEP.TRC(SCCGWA, BPB5171_AWA_5171.EFF_DT);
        CEP.TRC(SCCGWA, BPB5171_AWA_5171.EFF_TM);
        CEP.TRC(SCCGWA, BPB5171_AWA_5171.EXP_DT);
        CEP.TRC(SCCGWA, BPB5171_AWA_5171.EXP_TM);
        if (BPB5171_AWA_5171.EFF_DT == 0) {
            BPB5171_AWA_5171.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB5171_AWA_5171.EFF_TM == 0) {
            BPB5171_AWA_5171.EFF_TM = SCCGWA.COMM_AREA.TR_TIME;
        }
        if (BPB5171_AWA_5171.EXP_DT == 0) {
            BPB5171_AWA_5171.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB5171_AWA_5171.EXP_TM == 0) {
            BPB5171_AWA_5171.EXP_TM = SCCGWA.COMM_AREA.TR_TIME;
        }
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPB5171_AWA_5171.EXR_TYP;
        BPCPRMR.DAT_PTR = BPREXRT;
        S000_CALL_BPZPRMR();
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXRGROP);
        }
        CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.BASE_CCY);
        IBS.init(SCCGWA, BPCOIEC);
        BPCOIEC.CCY1 = BPB5171_AWA_5171.CCY;
        BPCOIEC.CCY2 = BPREXRT.DAT_TXT.BASE_CCY;
        S000_CALL_BPZSIEC();
        CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        WS_CCY = BPCOIEC.EXR_CODE.substring(0, 3);
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        WS_CORR_CCY = BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1);
    }
    public void B020_INQ_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQQTP);
        BPCQQTP.BR = BPB5171_AWA_5171.BR;
        BPCQQTP.EXR_TYP = BPB5171_AWA_5171.EXR_TYP;
        BPCQQTP.CCY = WS_CCY;
        BPCQQTP.CORR_CCY = WS_CORR_CCY;
        BPCQQTP.STR_DATE = BPB5171_AWA_5171.EFF_DT;
        BPCQQTP.STR_TIME = BPB5171_AWA_5171.EFF_TM;
        BPCQQTP.END_DATE = BPB5171_AWA_5171.EXP_DT;
        BPCQQTP.END_TIME = BPB5171_AWA_5171.EXP_TM;
        CEP.TRC(SCCGWA, BPCQQTP.STR_DATE);
        CEP.TRC(SCCGWA, BPCQQTP.STR_TIME);
        CEP.TRC(SCCGWA, BPCQQTP.END_DATE);
        CEP.TRC(SCCGWA, BPCQQTP.END_TIME);
        if ((BPB5171_AWA_5171.EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPB5171_AWA_5171.EFF_TM >= SCCGWA.COMM_AREA.TR_TIME) 
            || BPB5171_AWA_5171.EFF_DT > SCCGWA.COMM_AREA.AC_DATE) {
            B006_INQ_BPTTQP();
        } else {
            B003_INQ_PROC();
        }
    }
    public void B006_INQ_BPTTQP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTQP);
        BPRTQP.KEY.BR = BPB5171_AWA_5171.BR;
        BPRTQP.KEY.EXR_TYP = BPB5171_AWA_5171.EXR_TYP;
        BPRTQP.KEY.CCY = WS_CCY;
        BPRTQP.KEY.CORR_CCY = WS_CORR_CCY;
        CEP.TRC(SCCGWA, "DEVHZ298");
        T000_READ_REC_PROC();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            C000_OUT_TQP_DATA();
        }
    }
    public void T000_READ_REC_PROC() throws IOException,SQLException,Exception {
        BPTTQP_RD = new DBParm();
        BPTTQP_RD.TableName = "BPTTQP";
        BPTTQP_RD.where = "BR = :BPRTQP.KEY.BR "
            + "AND EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        IBS.READ(SCCGWA, BPRTQP, this, BPTTQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void C000_OUT_TQP_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_L_TOTAL_ROW = 1;
        WS_OUTPUT_DATA.WS_L_CURR_MAX_ROW = 1;
        WS_OUTPUT_DATA.WS_L_END_FLG = 'Y';
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_EXR_TYP = BPRTQP.KEY.EXR_TYP;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_BR = BPRTQP.KEY.BR;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_CCY = BPRTQP.KEY.CCY;
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPRTQP.KEY.EXR_TYP;
        BPREXRD.KEY.CCY = BPRTQP.KEY.CCY;
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_UNIT = BPREXRD.UNT;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_EFF_DT = BPRTQP.KEY.EFF_DT;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_EFF_TM = BPRTQP.KEY.EFF_TM;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_EXP_DT = 99991231;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_EXP_TM = 235959;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_CS_BUY = BPRTQP.CS_BUY;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_CS_SELL = BPRTQP.CS_SELL;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_FX_BUY = BPRTQP.FX_BUY;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_FX_SELL = BPRTQP.FX_SELL;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_CCS_BUY = BPRTQP.CCS_BUY;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_CCS_SELL = BPRTQP.CCS_SELL;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_CFX_BUY = BPRTQP.CFX_BUY;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_CFX_SELL = BPRTQP.CFX_SELL;
        WS_OUTPUT_DATA.WS_L_DATA[1-1].WS_L_LOC_MID = BPRTQP.LOC_MID;
        C000_FMT_OUTPUT();
    }
    public void C000_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP171";
        IBS.init(SCCGWA, BPCF171);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCF171);
        SCCFMT.DATA_PTR = BPCF171;
        SCCFMT.DATA_LEN = 9063;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B003_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTQPH);
        IBS.init(SCCGWA, BPCRTQPH);
        BPRTQPH.KEY.BR = BPCQQTP.BR;
        BPRTQPH.KEY.EXR_TYP = BPB5171_AWA_5171.EXR_TYP;
        BPRTQPH.KEY.CCY = WS_CCY;
        BPRTQPH.KEY.CORR_CCY = WS_CORR_CCY;
        BPRTQPH.EFF_DT = BPB5171_AWA_5171.EFF_DT;
        BPCRTQPH.STR_DATE = BPB5171_AWA_5171.EFF_DT;
        WS_I_S_DT.WS_I_S_DATE = BPB5171_AWA_5171.EFF_DT;
        BPRTQPH.EFF_TM = BPB5171_AWA_5171.EFF_TM;
        WS_I_S_DT.WS_I_S_TIME = BPB5171_AWA_5171.EFF_TM;
        BPRTQPH.KEY.EXP_DT = BPB5171_AWA_5171.EXP_DT;
        BPCRTQPH.END_DATE = BPB5171_AWA_5171.EXP_DT;
        WS_I_E_DT.WS_I_E_DATE = BPB5171_AWA_5171.EXP_DT;
        BPRTQPH.KEY.EXP_TM = BPB5171_AWA_5171.EXP_TM;
        WS_I_E_DT.WS_I_E_TIME = BPB5171_AWA_5171.EXP_TM;
        BPCRTQPH.INFO.FUNC = '7';
        S000_CALL_BPZTTQPH();
        BPCRTQPH.INFO.FUNC = 'R';
        S000_CALL_BPZTTQPH();
        while (BPCRTQPH.INFO.RTN_INFO != 'N') {
            IBS.init(SCCGWA, WS_R_S_DT);
            IBS.init(SCCGWA, WS_R_E_DT);
            WS_OUT_REC_FLG = ' ';
            WS_R_S_DT.WS_R_S_DATE = BPRTQPH.EFF_DT;
            WS_R_S_DT.WS_R_S_TIME = BPRTQPH.EFF_TM;
            WS_R_E_DT.WS_R_E_DATE = BPRTQPH.KEY.EXP_DT;
            WS_R_E_DT.WS_R_E_TIME = BPRTQPH.KEY.EXP_TM;
            R000_CHK_DATE_TIME();
            CEP.TRC(SCCGWA, BPOT5171_WS2);
            CEP.TRC(SCCGWA, WS_R_S_DT);
            CEP.TRC(SCCGWA, WS_R_E_DT);
            CEP.TRC(SCCGWA, WS_I_S_DT);
            CEP.TRC(SCCGWA, WS_I_E_DT);
            if (WS_OUT_REC_FLG == 'Y' 
                && WS_CNT < 50) {
                B003_03_OUT_DATA();
            }
            BPCRTQPH.INFO.FUNC = 'R';
            S000_CALL_BPZTTQPH();
        }
        if (BPCRTQPH.INFO.RTN_INFO == 'N') {
            WS_OUTPUT_DATA.WS_L_END_FLG = 'Y';
        }
        BPCRTQPH.INFO.FUNC = 'E';
        S000_CALL_BPZTTQPH();
        if (WS_CNT == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST);
        }
        WS_OUTPUT_DATA.WS_L_TOTAL_ROW = WS_CNT;
        C000_FMT_OUTPUT();
    }
    public void R000_CHK_DATE_TIME() throws IOException,SQLException,Exception {
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_R_S_DT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_I_S_DT);
        JIBS_tmp_str[4] = IBS.CLS2CPY(SCCGWA, WS_R_E_DT);
        JIBS_tmp_str[3] = IBS.CLS2CPY(SCCGWA, WS_I_S_DT);
        JIBS_tmp_str[6] = IBS.CLS2CPY(SCCGWA, WS_R_S_DT);
        JIBS_tmp_str[5] = IBS.CLS2CPY(SCCGWA, WS_I_S_DT);
        JIBS_tmp_str[9] = IBS.CLS2CPY(SCCGWA, WS_R_E_DT);
        JIBS_tmp_str[8] = IBS.CLS2CPY(SCCGWA, WS_I_E_DT);
        JIBS_tmp_str[11] = IBS.CLS2CPY(SCCGWA, WS_R_S_DT);
        JIBS_tmp_str[10] = IBS.CLS2CPY(SCCGWA, WS_I_E_DT);
        JIBS_tmp_str[14] = IBS.CLS2CPY(SCCGWA, WS_R_E_DT);
        JIBS_tmp_str[13] = IBS.CLS2CPY(SCCGWA, WS_I_E_DT);
        if ((JIBS_tmp_str[1].compareTo(JIBS_tmp_str[0]) <= 0 
            && JIBS_tmp_str[4].compareTo(JIBS_tmp_str[3]) >= 0) 
            || (JIBS_tmp_str[6].compareTo(JIBS_tmp_str[5]) >= 0 
            && JIBS_tmp_str[9].compareTo(JIBS_tmp_str[8]) <= 0) 
            || (JIBS_tmp_str[11].compareTo(JIBS_tmp_str[10]) <= 0 
            && JIBS_tmp_str[14].compareTo(JIBS_tmp_str[13]) >= 0)) {
            WS_OUT_REC_FLG = 'Y';
        }
    }
    public void B003_03_OUT_DATA() throws IOException,SQLException,Exception {
        WS_CNT += 1;
        IBS.init(SCCGWA, WS_OUTPUT_DATA);
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_EXR_TYP = BPRTQPH.KEY.EXR_TYP;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_BR = BPRTQPH.KEY.BR;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_CCY = BPRTQPH.KEY.CCY;
        IBS.init(SCCGWA, BPREXRD);
        IBS.init(SCCGWA, BPCTEXRM);
        BPREXRD.KEY.EXR_TYP = BPRTQPH.KEY.EXR_TYP;
        BPREXRD.KEY.CCY = BPRTQPH.KEY.CCY;
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_UNIT = BPREXRD.UNT;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_EFF_DT = BPRTQPH.EFF_DT;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_EFF_TM = BPRTQPH.EFF_TM;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_EXP_DT = BPRTQPH.KEY.EXP_DT;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_EXP_TM = BPRTQPH.KEY.EXP_TM;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_CS_BUY = BPRTQPH.CS_BUY;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_CS_SELL = BPRTQPH.CS_SELL;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_FX_BUY = BPRTQPH.FX_BUY;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_FX_SELL = BPRTQPH.FX_SELL;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_CCS_BUY = BPRTQPH.CCS_BUY;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_CCS_SELL = BPRTQPH.CCS_SELL;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_CFX_BUY = BPRTQPH.CFX_BUY;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_CFX_SELL = BPRTQPH.CFX_SELL;
        WS_OUTPUT_DATA.WS_L_DATA[WS_CNT-1].WS_L_LOC_MID = BPRTQP.LOC_MID;
    }
    public void S000_CALL_BPZQQTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INQ_TXN_QTP, BPCQQTP);
    }
    public void S000_CALL_BPZTTQPH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_DT);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXP_TM);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQPH.KEY.CORR_CCY);
        BPCRTQPH.INFO.POINTER = BPRTQPH;
        BPCRTQPH.INFO.LEN = 412;
        IBS.CALLCPN(SCCGWA, "BP-R-TQPH-B", BPCRTQPH);
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, "BP-R-EXRD-M", BPCTEXRM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_CODE, BPCOIEC);
        if (BPCOIEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCOIEC.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
