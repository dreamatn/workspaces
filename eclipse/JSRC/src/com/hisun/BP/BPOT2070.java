package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2070 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm BPTINVT_BR = new brParm();
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    String OUTPUT_FMT = "SCZ01";
    String BRANCH = "11";
    char QUERY_TOTAL = '2';
    String S_BRE_LBI = "BP-S-BRE-LBI        ";
    String S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String P_INQ_ORG = "BP-P-INQ-ORG        ";
    String R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    BPOT2070_WS_VARIABLES WS_VARIABLES = new BPOT2070_WS_VARIABLES();
    BPOT2070_WS_OUT_DATA WS_OUT_DATA = new BPOT2070_WS_OUT_DATA();
    BPOT2070_WS_COND_FLG WS_COND_FLG = new BPOT2070_WS_COND_FLG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBLBI BPCSBLBI = new BPCSBLBI();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPRINVT BPRINVT = new BPRINVT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPB2070_AWA_2070 AWA_2070;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2070 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AWA_2070 = new BPB2070_AWA_2070();
        IBS.init(SCCGWA, AWA_2070);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_2070);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AWA_2070);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_TLR_INFO_FOR_CN();
            if (pgmRtn) return;
            B020_BROWSE_INVT_PROC_FOR_CN();
            if (pgmRtn) return;
        } else {
            B010_CHECK_TLR_INFO();
            if (pgmRtn) return;
            B020_BROWSE_CLBI_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_TLR_INFO_FOR_CN() throws IOException,SQLException,Exception {
        if (AWA_2070.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AWA_2070.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BR_NOTFND;
                WS_VARIABLES.FLD_NO = AWA_2070.BR_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CEP.TRC(SCCGWA, BPCPQORG.BR);
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            if (BPCPQORG.ATTR != '2') {
                if (AWA_2070.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR157);
                }
            } else {
                if (BPCPQORG.ATTR == '2') {
                    CEP.TRC(SCCGWA, "DEV10");
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = AWA_2070.BR;
                    CEP.TRC(SCCGWA, BPCPQORG.BR);
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                    CEP.TRC(SCCGWA, BPCPQORG.SUPR_BR);
                    if (BPCPQORG.ATTR == '2') {
                        if (AWA_2070.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                            CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR158);
                        }
                    } else {
                        if (BPCPQORG.ATTR == '3') {
                            if (BPCPQORG.SUPR_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR159);
                            }
                        } else {
                            CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR160);
                        }
                    }
                }
            }
        }
        if (AWA_2070.DT != 0) {
            if (AWA_2070.DT > SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR150);
            }
        }
        if (AWA_2070.TLR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = AWA_2070.TLR_NO;
            IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY", BPCFTLRQ);
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
                CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
            }
        }
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-QRY-BR-CHK", BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void B010_CHECK_TLR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_CASHLIB_TLR;
            WS_VARIABLES.FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = AWA_2070.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_BR_NOTFND;
            WS_VARIABLES.FLD_NO = AWA_2070.BR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_CLBI_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLBI);
        if (BPCPQORG.TYP.equalsIgnoreCase(BRANCH)) {
            BPCSBLBI.FUNC = 'A';
        } else {
            BPCSBLBI.FUNC = 'P';
        }
        BPCSBLBI.OUTPUT_FMT = OUTPUT_FMT;
        BPCSBLBI.BR = AWA_2070.BR;
        BPCSBLBI.PLBOX_NO = AWA_2070.PLBOX_NO;
        BPCSBLBI.CCY = AWA_2070.CCY;
        BPCSBLBI.PAR_VAL = AWA_2070.PAR_VAL;
        BPCSBLBI.M_FLG = AWA_2070.M_FLG;
        S000_CALL_BPZSBLBI();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_CLBI_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBLBI);
        if (AWA_2070.QRY_TYPE == QUERY_TOTAL) {
            BPCSBLBI.FUNC = 'A';
        } else {
            BPCSBLBI.FUNC = 'P';
        }
        BPCSBLBI.OUTPUT_FMT = OUTPUT_FMT;
        BPCSBLBI.BR = AWA_2070.BR;
        BPCSBLBI.PLBOX_NO = WS_OUT_DATA.PLBOX_NO;
        BPCSBLBI.CCY = AWA_2070.CCY;
        BPCSBLBI.PAR_VAL = AWA_2070.PAR_VAL;
        BPCSBLBI.M_FLG = AWA_2070.M_FLG;
        BPCSBLBI.TLR_NO = AWA_2070.TLR_NO;
        S000_CALL_BPZSBLBI();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_INVT_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINVT);
        BPRINVT.BR = AWA_2070.BR;
        BPRINVT.TLR_D = AWA_2070.TLR_NO;
        BPRINVT.KEY.DATE = AWA_2070.DT;
        BPRINVT.CB_TYP = AWA_2070.CB_TYP;
        CEP.TRC(SCCGWA, AWA_2070.BR);
        CEP.TRC(SCCGWA, AWA_2070.TLR_NO);
        CEP.TRC(SCCGWA, AWA_2070.DT);
        CEP.TRC(SCCGWA, AWA_2070.CB_TYP);
        R000_INVT_PROC();
        if (pgmRtn) return;
    }
    public void R000_INVT_PROC() throws IOException,SQLException,Exception {
        B010_STARTBR_BPTINVT();
        if (pgmRtn) return;
        B020_READNEXT_BPTINVT();
        if (pgmRtn) return;
        WS_VARIABLES.CNT = 0;
        while (WS_COND_FLG.TBL_FLAG != 'N') {
            WS_VARIABLES.CNT = WS_VARIABLES.CNT + 1;
            if (WS_VARIABLES.CNT == 1) {
                B040_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            if (BPRINVT.INVNTYP == '1' 
                || BPRINVT.INVNTYP == '2') {
                B040_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            B020_READNEXT_BPTINVT();
            if (pgmRtn) return;
        }
        B030_ENDBR_BPTINVT();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_BPTINVT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRINVT.BR);
        CEP.TRC(SCCGWA, BPRINVT.TLR_D);
        CEP.TRC(SCCGWA, BPRINVT.KEY.DATE);
        CEP.TRC(SCCGWA, BPRINVT.CB_TYP);
        if (BPRINVT.BR == 0 
                && BPRINVT.TLR_D.trim().length() == 0 
                && BPRINVT.KEY.DATE == 0) {
            CEP.TRC(SCCGWA, "DEV1");
            B010_STARTBR_BPTINVT1();
            if (pgmRtn) return;
        } else if (BPRINVT.BR == 0 
                && BPRINVT.TLR_D.trim().length() == 0 
                && BPRINVT.KEY.DATE != 0) {
            CEP.TRC(SCCGWA, "DEV2");
            B010_STARTBR_BPTINVT2();
            if (pgmRtn) return;
        } else if (BPRINVT.BR == 0 
                && BPRINVT.TLR_D.trim().length() > 0 
                && BPRINVT.KEY.DATE == 0) {
            CEP.TRC(SCCGWA, "DEV3");
            B010_STARTBR_BPTINVT3();
            if (pgmRtn) return;
        } else if (BPRINVT.BR == 0 
                && BPRINVT.TLR_D.trim().length() > 0 
                && BPRINVT.KEY.DATE != 0) {
            CEP.TRC(SCCGWA, "DEV4");
            B010_STARTBR_BPTINVT4();
            if (pgmRtn) return;
        } else if (BPRINVT.BR != 0 
                && BPRINVT.TLR_D.trim().length() == 0 
                && BPRINVT.KEY.DATE == 0) {
            CEP.TRC(SCCGWA, "DEV5");
            B010_STARTBR_BPTINVT5();
            if (pgmRtn) return;
        } else if (BPRINVT.BR != 0 
                && BPRINVT.TLR_D.trim().length() == 0 
                && BPRINVT.KEY.DATE != 0) {
            CEP.TRC(SCCGWA, "DEV6");
            B010_STARTBR_BPTINVT6();
            if (pgmRtn) return;
        } else if (BPRINVT.BR != 0 
                && BPRINVT.TLR_D.trim().length() > 0 
                && BPRINVT.KEY.DATE == 0) {
            CEP.TRC(SCCGWA, "DEV7");
            B010_STARTBR_BPTINVT7();
            if (pgmRtn) return;
        } else if (BPRINVT.BR != 0 
                && BPRINVT.TLR_D.trim().length() > 0 
                && BPRINVT.KEY.DATE != 0) {
            CEP.TRC(SCCGWA, "DEV8");
            B010_STARTBR_BPTINVT8();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "DEV9");
            CEP.ERR(SCCGWA, ERROR_MSG.BP_INPUT_ERROR151);
        }
    }
    public void B010_STARTBR_BPTINVT1() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "CB_TYP = :BPRINVT.CB_TYP";
        BPTINVT_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
    }
    public void B010_STARTBR_BPTINVT2() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "'DATE' = :BPRINVT.KEY.DATE "
            + "AND CB_TYP = :BPRINVT.CB_TYP";
        BPTINVT_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
    }
    public void B010_STARTBR_BPTINVT3() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "TLR_D = :BPRINVT.TLR_D "
            + "AND CB_TYP = :BPRINVT.CB_TYP";
        BPTINVT_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
    }
    public void B010_STARTBR_BPTINVT4() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "TLR_D = :BPRINVT.TLR_D "
            + "AND 'DATE' = :BPRINVT.KEY.DATE "
            + "AND CB_TYP = :BPRINVT.CB_TYP";
        BPTINVT_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
    }
    public void B010_STARTBR_BPTINVT5() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "BR = :BPRINVT.BR "
            + "AND CB_TYP = :BPRINVT.CB_TYP";
        BPTINVT_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
    }
    public void B010_STARTBR_BPTINVT6() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "BR = :BPRINVT.BR "
            + "AND 'DATE' = :BPRINVT.KEY.DATE "
            + "AND CB_TYP = :BPRINVT.CB_TYP";
        BPTINVT_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
    }
    public void B010_STARTBR_BPTINVT7() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "BR = :BPRINVT.BR "
            + "AND TLR_D = :BPRINVT.TLR_D "
            + "AND CB_TYP = :BPRINVT.CB_TYP";
        BPTINVT_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
    }
    public void B010_STARTBR_BPTINVT8() throws IOException,SQLException,Exception {
        BPTINVT_BR.rp = new DBParm();
        BPTINVT_BR.rp.TableName = "BPTINVT";
        BPTINVT_BR.rp.where = "BR = :BPRINVT.BR "
            + "AND TLR_D = :BPRINVT.TLR_D "
            + "AND 'DATE' = :BPRINVT.KEY.DATE "
            + "AND CB_TYP = :BPRINVT.CB_TYP";
        BPTINVT_BR.rp.order = "TS";
        IBS.STARTBR(SCCGWA, BPRINVT, this, BPTINVT_BR);
    }
    public void B020_READNEXT_BPTINVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINVT);
        IBS.READNEXT(SCCGWA, BPRINVT, this, BPTINVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.TBL_FLAG = 'N';
        }
    }
    public void B030_ENDBR_BPTINVT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTINVT_BR);
    }
    public void B040_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 573;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.TR_DT = BPRINVT.KEY.DATE;
        WS_OUT_DATA.JRNNO = BPRINVT.KEY.JRNNO;
        WS_OUT_DATA.CB_TYP = BPRINVT.CB_TYP;
        WS_OUT_DATA.TX_TYP = BPRINVT.INVNTYP;
        WS_OUT_DATA.VB_FLG = BPRINVT.PLBOX_TYPE;
        WS_OUT_DATA.PLBOX_NO = BPRINVT.PLBOX_NO;
        WS_OUT_DATA.CCY = BPRINVT.CCY;
        WS_OUT_DATA.MACH_AMT = BPRINVT.MACH_AMT;
        WS_OUT_DATA.ACTU_AMT = BPRINVT.ACTU_AMT;
        WS_OUT_DATA.STS = BPRINVT.STS;
        if (BPRINVT.TS == null) BPRINVT.TS = "";
        JIBS_tmp_int = BPRINVT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRINVT.TS += " ";
        JIBS_tmp_str[0] = "" + WS_OUT_DATA.TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_OUT_DATA.TM;
        JIBS_NumStr = BPRINVT.TS.substring(12 - 1, 12 + 2 - 1) + JIBS_NumStr.substring(2);
        WS_OUT_DATA.TM = Integer.parseInt(JIBS_NumStr);
        if (BPRINVT.TS == null) BPRINVT.TS = "";
        JIBS_tmp_int = BPRINVT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRINVT.TS += " ";
        JIBS_tmp_str[0] = "" + WS_OUT_DATA.TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_OUT_DATA.TM;
        JIBS_NumStr = JIBS_NumStr.substring(0, 3 - 1) + BPRINVT.TS.substring(15 - 1, 15 + 2 - 1) + JIBS_NumStr.substring(3 + 2 - 1);
        WS_OUT_DATA.TM = Integer.parseInt(JIBS_NumStr);
        if (BPRINVT.TS == null) BPRINVT.TS = "";
        JIBS_tmp_int = BPRINVT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRINVT.TS += " ";
        JIBS_tmp_str[0] = "" + WS_OUT_DATA.TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_OUT_DATA.TM;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + BPRINVT.TS.substring(18 - 1, 18 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        WS_OUT_DATA.TM = Integer.parseInt(JIBS_NumStr);
        if (BPRINVT.TS == null) BPRINVT.TS = "";
        JIBS_tmp_int = BPRINVT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRINVT.TS += " ";
        CEP.TRC(SCCGWA, BPRINVT.TS.substring(12 - 1, 12 + 2 - 1));
        if (BPRINVT.TS == null) BPRINVT.TS = "";
        JIBS_tmp_int = BPRINVT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRINVT.TS += " ";
        CEP.TRC(SCCGWA, BPRINVT.TS.substring(15 - 1, 15 + 2 - 1));
        if (BPRINVT.TS == null) BPRINVT.TS = "";
        JIBS_tmp_int = BPRINVT.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRINVT.TS += " ";
        CEP.TRC(SCCGWA, BPRINVT.TS.substring(18 - 1, 18 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_OUT_DATA.TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + WS_OUT_DATA.TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + WS_OUT_DATA.TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        CEP.TRC(SCCGWA, BPRINVT.TS);
        CEP.TRC(SCCGWA, WS_OUT_DATA.TR_DT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.JRNNO);
        CEP.TRC(SCCGWA, WS_OUT_DATA.CB_TYP);
        CEP.TRC(SCCGWA, WS_OUT_DATA.TM);
        CEP.TRC(SCCGWA, WS_OUT_DATA.TX_TYP);
        CEP.TRC(SCCGWA, WS_OUT_DATA.VB_FLG);
        CEP.TRC(SCCGWA, WS_OUT_DATA.PLBOX_NO);
        CEP.TRC(SCCGWA, WS_OUT_DATA.CCY);
        CEP.TRC(SCCGWA, WS_OUT_DATA.MACH_AMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.ACTU_AMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.STS);
        CEP.TRC(SCCGWA, BPRINVT.REDEFINES28.DATA_TEXT1);
        CEP.TRC(SCCGWA, BPRINVT.REDEFINES28.FILLER2.REC_DATA1[1-1].MACH_PVAL);
        CEP.TRC(SCCGWA, BPRINVT.REDEFINES28.FILLER2.REC_DATA1[1-1].ACTU_CASH_NUM);
        for (WS_VARIABLES.CNT = 1; WS_VARIABLES.CNT <= 20; WS_VARIABLES.CNT += 1) {
            WS_OUT_DATA.WS_PVAL_INFO[WS_VARIABLES.CNT-1].PVAL = BPRINVT.REDEFINES28.FILLER2.REC_DATA1[WS_VARIABLES.CNT-1].MACH_PVAL;
            WS_OUT_DATA.WS_PVAL_INFO[WS_VARIABLES.CNT-1].NUM = BPRINVT.REDEFINES28.FILLER2.REC_DATA1[WS_VARIABLES.CNT-1].ACTU_CASH_NUM;
            CEP.TRC(SCCGWA, WS_VARIABLES.CNT);
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_PVAL_INFO[WS_VARIABLES.CNT-1].PVAL);
            CEP.TRC(SCCGWA, WS_OUT_DATA.WS_PVAL_INFO[WS_VARIABLES.CNT-1].NUM);
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        SCCMPAG.DATA_LEN = 573;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZSBLBI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_BRE_LBI, BPCSBLBI);
        if (BPCSBLBI.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBLBI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_VARIABLES.ERR_MSG, WS_VARIABLES.FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
