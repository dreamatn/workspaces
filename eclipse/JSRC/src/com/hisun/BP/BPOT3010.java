package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3010 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP150";
    String CPN_S_IN_V = "BP-S-BV-IN-V     ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_U_CARD_STS = "DC-U-CARD-ORDER-STS";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String CPN_U_BV_OUT = "BP-U-TLR-BV-OUT     ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    long WS_COMP_BEGNO = 0;
    long WS_COMP_ENDNO = 0;
    long WS_COMP_TEMP1 = 0;
    int WS_COMP_LEN = 0;
    short WS_POS = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCSBVIV BPCSBVIV = new BPCSBVIV();
    DCCUUORD DCCUUORD = new DCCUUORD();
    DCCUGTBV DCCUGTBV = new DCCUGTBV();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    BPCUBVOU BPCUBVOU = new BPCUBVOU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPB3010_AWA_3010 BPB3010_AWA_3010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3010_AWA_3010>");
        BPB3010_AWA_3010 = (BPB3010_AWA_3010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_LINK_BVIV_COMPONENT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPB3010_AWA_3010.BV_DATA[1-1].CODE;
        S000_CALL_BPZFBVQU();
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.COST_PRICE);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.SER_CHARGE);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].END_NO);
        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0) {
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                CEP.TRC(SCCGWA, WS_I);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0) {
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
                CEP.TRC(SCCGWA, WS_I);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].END_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPB3010_AWA_3010.BV_DATA[1-1].HEAD_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_HEADNO;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].HEAD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.trim().length() > 0 
                || BPB3010_AWA_3010.BV_DATA[1-1].END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            CEP.TRC(SCCGWA, WS_BVNO_LEN);
            CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO);
            CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].END_NO);
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO += " ";
            if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].END_NO = "";
            JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].END_NO += " ";
            if (BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPB3010_AWA_3010.BV_DATA[1-1].END_NO.substring(0, WS_BVNO_LEN));
            CEP.TRC(SCCGWA, WS_COMP_BEGNO);
            CEP.TRC(SCCGWA, WS_COMP_ENDNO);
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                WS_FLD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO_NO;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].NUM);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.BV_CFLG);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.TYPE);
            if (BPCFBVQU.TX_DATA.BV_CFLG == 'Y') {
                for (WS_I = 0; WS_I != BPB3010_AWA_3010.BV_DATA[1-1].NUM; WS_I += 1) {
                    IBS.init(SCCGWA, DCCUUORD);
                    IBS.init(SCCGWA, DCCUGTBV);
                    CEP.TRC(SCCGWA, WS_I);
                    if (WS_I == 0) {
                        CEP.TRC(SCCGWA, WS_COMP_BEGNO);
                        CEP.TRC(SCCGWA, DCCUGTBV.INPUT.CARD_NO);
                        WS_COMP_LEN = 20;
                        CEP.TRC(SCCGWA, WS_COMP_LEN);
                        WS_POS = (short) (WS_COMP_LEN - WS_BVNO_LEN + 1);
                        CEP.TRC(SCCGWA, WS_POS);
                        if (BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO == null) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO = "";
                        JIBS_tmp_int = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO += " ";
                        DCCUGTBV.INPUT.CARD_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO.substring(0, WS_BVNO_LEN);
                        CEP.TRC(SCCGWA, DCCUGTBV.INPUT.CARD_NO);
                        S00_CALL_DCZUGTBV();
                        CEP.TRC(SCCGWA, DCCUGTBV.OUTPUT.BV_CODE);
                        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].CODE);
                        CEP.TRC(SCCGWA, DCCUGTBV.INPUT.CARD_NO);
                        if (!DCCUGTBV.OUTPUT.BV_CODE.equalsIgnoreCase(BPB3010_AWA_3010.BV_DATA[1-1].CODE)) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCAD_NOT_MATCH;
                            S000_ERR_MSG_PROC();
                        }
                        if (DCCUGTBV.INPUT.CARD_NO.trim().length() == 0) WS_COMP_TEMP1 = 0;
                        else WS_COMP_TEMP1 = Long.parseLong(DCCUGTBV.INPUT.CARD_NO);
                        DCCUUORD.CARD_NO = DCCUGTBV.INPUT.CARD_NO;
                        CEP.TRC(SCCGWA, DCCUUORD.CARD_NO);
                        S00_CALL_DCZUUORD();
                    } else {
                        CEP.TRC(SCCGWA, WS_COMP_TEMP1);
                        IBS.init(SCCGWA, BPCSNOCK);
                        BPCSNOCK.BV_CODE = BPB3010_AWA_3010.BV_DATA[1-1].CODE;
                        JIBS_tmp_str[0] = "" + WS_COMP_TEMP1;
                        JIBS_tmp_int = JIBS_tmp_str[0].length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                        BPCSNOCK.BV_NO = JIBS_tmp_str[0].substring(WS_POS - 1, WS_POS + WS_BVNO_LEN - 1);
                        BPCSNOCK.FUNC = '4';
                        CEP.TRC(SCCGWA, BPCSNOCK.BV_NO);
                        S000_CALL_BPZSNOCK();
                        DCCUUORD.CARD_NO = BPCSNOCK.NEXT_NO;
                        if (BPCSNOCK.NEXT_NO.trim().length() == 0) WS_COMP_TEMP1 = 0;
                        else WS_COMP_TEMP1 = Long.parseLong(BPCSNOCK.NEXT_NO);
                        CEP.TRC(SCCGWA, BPCSNOCK.NEXT_NO);
                        CEP.TRC(SCCGWA, "xgq:");
                        CEP.TRC(SCCGWA, DCCUUORD.CARD_NO);
                        S00_CALL_DCZUUORD();
                    }
                }
            }
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPB3010_AWA_3010.BV_DATA[1-1].CODE;
            BPCSNOCK.BEG_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO;
            BPCSNOCK.END_NO = BPB3010_AWA_3010.BV_DATA[1-1].END_NO;
            BPCSNOCK.NUM = BPB3010_AWA_3010.BV_DATA[1-1].NUM;
            CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO);
            CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].END_NO);
            CEP.TRC(SCCGWA, BPCSNOCK.NUM);
            BPCSNOCK.FUNC = '1';
            S000_CALL_BPZSNOCK();
        }
    }
    public void B020_LINK_BVIV_COMPONENT() throws IOException,SQLException,Exception {
        if (BPCFBVQU.TX_DATA.REL_BV.trim().length() > 0) {
            CEP.TRC(SCCGWA, "XGQ");
            IBS.init(SCCGWA, BPCUBVOU);
            BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUBVOU.TYPE = BPCFBVQU.TX_DATA.TYPE;
            BPCUBVOU.BV_CODE = BPCFBVQU.TX_DATA.REL_BV;
            BPCUBVOU.NUM = BPCSNOCK.NUM;
            BPCUBVOU.VB_FLG = '1';
            S000_CALL_BPZUBVOU();
        } else {
        }
        IBS.init(SCCGWA, BPCSBVIV);
        CEP.TRC(SCCGWA, BPB3010_AWA_3010.BV_DATA[1-1].NUM);
        BPCSBVIV.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCSBVIV.CODE = BPB3010_AWA_3010.BV_DATA[1-1].CODE;
        CEP.TRC(SCCGWA, BPCSBVIV.CODE);
        BPCSBVIV.ENM = BPB3010_AWA_3010.BV_DATA[1-1].ENM;
        BPCSBVIV.CNM = BPB3010_AWA_3010.BV_DATA[1-1].CNM;
        BPCSBVIV.HEAD_NO = BPB3010_AWA_3010.BV_DATA[1-1].HEAD_NO;
        BPCSBVIV.BEG_NO = BPB3010_AWA_3010.BV_DATA[1-1].BEG_NO;
        BPCSBVIV.END_NO = BPB3010_AWA_3010.BV_DATA[1-1].END_NO;
        BPCSBVIV.NUM = BPB3010_AWA_3010.BV_DATA[1-1].NUM;
        BPCSBVIV.VIL_TYP = BPB3010_AWA_3010.VIL_TYP;
        CEP.TRC(SCCGWA, BPCSBVIV.VIL_TYP);
        S00_CALL_BPZSBVIV();
        BPB3010_AWA_3010.MOV_DT = BPCSBVIV.MOVE_DT;
        BPB3010_AWA_3010.CONF_NO = BPCSBVIV.CONF_NO;
    }
    public void S000_CALL_BPZUBVOU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BV_OUT, BPCUBVOU);
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S00_CALL_BPZSBVIV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_IN_V, BPCSBVIV);
    }
    public void S00_CALL_DCZUUORD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CARD_STS, DCCUUORD);
    }
    public void S00_CALL_DCZUGTBV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CPN-DC-DCZUGTBV", DCCUGTBV);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
