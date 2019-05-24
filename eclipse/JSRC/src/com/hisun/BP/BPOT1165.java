package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1165 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP064";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_FCPN_MAINTAIN = "BP-F-S-MAINTAIN-FCPN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    short WS_FEE_NO = 0;
    short WS_FEE_TEMP = 0;
    short WS_FEE_NEXT = 0;
    char WS_INPUT_ENDED = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFCPN BPCOFCPN = new BPCOFCPN();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPC1165 BPC1165;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1165 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC1165 = new BPC1165();
        IBS.init(SCCGWA, BPC1165);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC1165);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPDATE_CPN_FEE();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPC1165.CPNT_ID.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CPNT_ID_NOTIN;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPC1165.EFF_DT;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPC1165.EXP_DT;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPC1165.EXP_DT <= BPC1165.EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPC1165.DATA[1-1].FEE_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FARM_TYP_ERR;
            S000_ERR_MSG_PROC();
        } else {
            if (BPC1165.DATA[1-1].LNK_FLG == ' ') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LNK_FLG_NOTIN;
                S000_ERR_MSG_PROC_CONTINUE();
            } else {
                if (BPC1165.DATA[1-1].LNK_FLG != '0' 
                    && BPC1165.DATA[1-1].LNK_FLG != '1') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LNK_FLG_INVALID;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        for (WS_FEE_NO = 1; WS_FEE_NO <= 20; WS_FEE_NO += 1) {
            if (BPC1165.DATA[WS_FEE_NO-1].FEE_CD.trim().length() > 0) {
                WS_FEE_TEMP = (short) (WS_FEE_NO + 1);
                for (WS_FEE_NEXT = WS_FEE_TEMP; WS_FEE_NEXT <= 20; WS_FEE_NEXT += 1) {
                    if (BPC1165.DATA[WS_FEE_NO-1].FEE_CD.equalsIgnoreCase(BPC1165.DATA[WS_FEE_NEXT-1].FEE_CD)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_DUB;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                }
                if (BPC1165.DATA[WS_FEE_NO-1].LNK_FLG == ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LNK_FLG_NOTIN;
                    S000_ERR_MSG_PROC_CONTINUE();
                } else {
                    if (BPC1165.DATA[WS_FEE_NO-1].LNK_FLG != '0' 
                        && BPC1165.DATA[WS_FEE_NO-1].LNK_FLG != '1') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LNK_FLG_INVALID;
                        S000_ERR_MSG_PROC_CONTINUE();
                    }
                }
                if (WS_INPUT_ENDED == 'Y') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_INFO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else {
                WS_INPUT_ENDED = 'Y';
                if (BPC1165.DATA[WS_FEE_NO-1].LNK_FLG != ' ') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            }
        }
        R000_CHECK_RESULT_PROC();
    }
    public void B020_UPDATE_CPN_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFCPN);
        BPCOFCPN.FUNC = 'U';
        BPCOFCPN.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZFSCPN();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFCPN.KEY.CPNT_ID = BPC1165.CPNT_ID;
        BPCOFCPN.VAL.EFF_DATE = BPC1165.EFF_DT;
        BPCOFCPN.VAL.EXP_DATE = BPC1165.EXP_DT;
        for (WS_CNT1 = 1; WS_CNT1 <= 20; WS_CNT1 += 1) {
            BPCOFCPN.VAL.CPN_DATE[WS_CNT1-1].FEE_CODE = BPC1165.DATA[WS_CNT1-1].FEE_CD;
            BPCOFCPN.VAL.CPN_DATE[WS_CNT1-1].LNK_FLG = BPC1165.DATA[WS_CNT1-1].LNK_FLG;
            BPCOFCPN.VAL.CPN_DATE[WS_CNT1-1].STR_CND = BPC1165.DATA[WS_CNT1-1].STR_CND;
        }
        BPCOFCPN.VAL.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOFCPN.VAL.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.TRC(SCCGWA, "ERROR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFSCPN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_FCPN_MAINTAIN, BPCOFCPN);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
