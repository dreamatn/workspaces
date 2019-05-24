package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPFPDT {
    DBParm BPTFSCH_RD;
    DBParm BPTFCTR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_FPDT = "BP-F-R-FE-UNCHG-DTL";
    String CPN_U_DCZUCINF = "DC-U-CARD-INF";
    String CPN_U_DDCSCINM = "DD-SVR-CI-NAME";
    String WS_ERR_MSG = " ";
    char WS_CYC_DTL_FLG = ' ';
    char WS_CCL_DTL_FLG = ' ';
    char WS_OWE_DTL_FLG = ' ';
    char WS_SCH_DTL_FLG = ' ';
    char WS_CTR_DTL_FLG = ' ';
    char WS_FEE_STATUS = ' ';
    char WS_FLAG = ' ';
    char WS_PROC_TYPE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFADT BPRFADT = new BPRFADT();
    BPRFPDT BPRFPDT = new BPRFPDT();
    CICACCU CICACCU = new CICACCU();
    BPCRFPDT BPCRFPDT = new BPCRFPDT();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPRFCTR BPRFCTR = new BPRFCTR();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCSCINM DDCSCINM = new DDCSCINM();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGMSG SCCGMSG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPFPDT BPCPFPDT;
    public void MP(SCCGWA SCCGWA, BPCPFPDT BPCPFPDT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPFPDT = BPCPFPDT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPFPDT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPFPDT.OUTPUT.PCHG_FLG = 'N';
        BPCPFPDT.OUTPUT.UNCHG_FLG = 'N';
        BPCPFPDT.OUTPUT.FSCH_FLG = 'N';
        BPCPFPDT.OUTPUT.FCTR_FLG = 'N';
        BPCPFPDT.OUTPUT.MAIN_FLG = 'N';
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCPFPDT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_FUNCTION_PROCESS();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_PROC_TYPE = ' ';
        if (BPCPFPDT.INPUT.AC.trim().length() == 0 
            && BPCPFPDT.INPUT.CI_NO.trim().length() == 0 
            && BPCPFPDT.INPUT.CARD_PSBK_NO.trim().length() == 0 
            && BPCPFPDT.INPUT.PRDT_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_NONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCPFPDT.INPUT.AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU);
            CICACCU.DATA.AGR_NO = BPCPFPDT.INPUT.AC;
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
            WS_PROC_TYPE = 'A';
        }
        if (BPCPFPDT.INPUT.CI_NO.trim().length() > 0 
            && BPCPFPDT.INPUT.CARD_PSBK_NO.trim().length() == 0 
            && BPCPFPDT.INPUT.AC.trim().length() == 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCPFPDT.INPUT.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            WS_PROC_TYPE = 'C';
        }
        if (BPCPFPDT.INPUT.CARD_PSBK_NO.trim().length() > 0 
            && BPCPFPDT.INPUT.AC.trim().length() == 0) {
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = BPCPFPDT.INPUT.CARD_PSBK_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            if (DCCUCINF.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_PROC_TYPE = 'D';
        }
        if (BPCPFPDT.INPUT.PRDT_CODE.trim().length() > 0) {
            WS_PROC_TYPE = 'P';
        }
        if (WS_PROC_TYPE == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_NONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_FUNCTION_PROCESS() throws IOException,SQLException,Exception {
        if (BPCPFPDT.INPUT.FDT_TYP == '0') {
            C210_CYC_DTL_PROC();
            if (pgmRtn) return;
        } else if (BPCPFPDT.INPUT.FDT_TYP == '1') {
            C220_CCL_DTL_PROC();
            if (pgmRtn) return;
        } else if (BPCPFPDT.INPUT.FDT_TYP == '2') {
            C230_OWE_DTL_PROC();
            if (pgmRtn) return;
        } else {
            C210_CYC_DTL_PROC();
            if (pgmRtn) return;
            C220_CCL_DTL_PROC();
            if (pgmRtn) return;
            C230_OWE_DTL_PROC();
            if (pgmRtn) return;
        }
        C240_SCH_DTL_PROC();
        if (pgmRtn) return;
        C250_CTR_DTL_PROC();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CYC_DTL_FLG);
        CEP.TRC(SCCGWA, WS_CCL_DTL_FLG);
        CEP.TRC(SCCGWA, WS_SCH_DTL_FLG);
        CEP.TRC(SCCGWA, WS_CTR_DTL_FLG);
        if (WS_CYC_DTL_FLG == 'Y' 
            || WS_CCL_DTL_FLG == 'Y') {
            BPCPFPDT.OUTPUT.PCHG_FLG = 'Y';
        }
        if (WS_OWE_DTL_FLG == 'Y') {
            BPCPFPDT.OUTPUT.UNCHG_FLG = 'Y';
        }
        if (WS_SCH_DTL_FLG == 'Y') {
            BPCPFPDT.OUTPUT.FSCH_FLG = 'Y';
        }
        if (WS_CTR_DTL_FLG == 'Y') {
            BPCPFPDT.OUTPUT.FCTR_FLG = 'Y';
        }
        if (WS_CYC_DTL_FLG == 'Y' 
            || WS_CCL_DTL_FLG == 'Y' 
            || WS_OWE_DTL_FLG == 'Y' 
            || WS_SCH_DTL_FLG == 'Y' 
            || WS_CTR_DTL_FLG == 'Y') {
            BPCPFPDT.OUTPUT.MAIN_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.PCHG_FLG);
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.UNCHG_FLG);
        CEP.TRC(SCCGWA, BPCPFPDT.OUTPUT.MAIN_FLG);
    }
    public void C210_CYC_DTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        BPCRFPDT.INFO.FDT_TYP = '0';
        WS_CYC_DTL_FLG = 'N';
        if (WS_PROC_TYPE == 'A') {
            BPRFADT.CHG_AC = BPCPFPDT.INPUT.AC;
            BPCRFPDT.INFO.FUNC = 'F';
        } else if (WS_PROC_TYPE == 'D') {
            BPRFADT.CARD_PSBK_NO = BPCPFPDT.INPUT.CARD_PSBK_NO;
            BPCRFPDT.INFO.FUNC = 'H';
        } else if (WS_PROC_TYPE == 'C') {
            BPRFADT.TX_CI = BPCPFPDT.INPUT.CI_NO;
            BPCRFPDT.INFO.FUNC = 'G';
        } else if (WS_PROC_TYPE == 'P') {
            BPRFADT.PRDT_CODE = BPCPFPDT.INPUT.PRDT_CODE;
            BPCRFPDT.INFO.FUNC = 'P';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_NONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'F') {
            WS_CYC_DTL_FLG = 'Y';
        }
    }
    public void C220_CCL_DTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFADT);
        BPCRFPDT.INFO.FDT_TYP = '1';
        WS_CCL_DTL_FLG = 'N';
        if (WS_PROC_TYPE == 'A') {
            BPRFADT.CHG_AC = BPCPFPDT.INPUT.AC;
            BPCRFPDT.INFO.FUNC = 'F';
        } else if (WS_PROC_TYPE == 'D') {
            BPRFADT.CARD_PSBK_NO = BPCPFPDT.INPUT.CARD_PSBK_NO;
            BPCRFPDT.INFO.FUNC = 'H';
        } else if (WS_PROC_TYPE == 'C') {
            BPRFADT.TX_CI = BPCPFPDT.INPUT.CI_NO;
            BPCRFPDT.INFO.FUNC = 'G';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_NONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'F') {
            WS_CYC_DTL_FLG = 'Y';
        }
    }
    public void C230_OWE_DTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFPDT);
        IBS.init(SCCGWA, BPRFPDT);
        BPCRFPDT.INFO.FDT_TYP = '2';
        WS_OWE_DTL_FLG = 'N';
        if (WS_PROC_TYPE == 'A') {
            BPRFPDT.CHG_AC = BPCPFPDT.INPUT.AC;
            BPCRFPDT.INFO.FUNC = 'F';
        } else if (WS_PROC_TYPE == 'D') {
            BPRFPDT.CARD_PSBK_NO = BPCPFPDT.INPUT.CARD_PSBK_NO;
            BPCRFPDT.INFO.FUNC = 'H';
        } else if (WS_PROC_TYPE == 'C') {
            BPRFPDT.TX_CI = BPCPFPDT.INPUT.CI_NO;
            BPCRFPDT.INFO.FUNC = 'G';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_NONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S000_CALL_BPZRFPDT();
        if (pgmRtn) return;
        if (BPCRFPDT.RETURN_INFO == 'F') {
            WS_CYC_DTL_FLG = 'Y';
        }
    }
    public void C240_SCH_DTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        WS_SCH_DTL_FLG = 'N';
        if (WS_PROC_TYPE == 'A') {
            BPRFSCH.CHARGE_AC = BPCPFPDT.INPUT.AC;
            BPRFSCH.FEE_STATUS = '0';
            CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
            T000_READ_BPTFSCH_1();
            if (pgmRtn) return;
            if (WS_FLAG == 'N') {
                BPRFSCH.FEE_STATUS = '1';
                CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
                T000_READ_BPTFSCH_1();
                if (pgmRtn) return;
            }
        } else if (WS_PROC_TYPE == 'D') {
            BPRFSCH.CARD_PSBK_NO = BPCPFPDT.INPUT.CARD_PSBK_NO;
            BPRFSCH.FEE_STATUS = '0';
            CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
            T000_READ_BPTFSCH_2();
            if (pgmRtn) return;
            if (WS_FLAG == 'N') {
                BPRFSCH.FEE_STATUS = '1';
                CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
                T000_READ_BPTFSCH_2();
                if (pgmRtn) return;
            }
        } else if (WS_PROC_TYPE == 'C') {
            BPRFSCH.CI_NO = BPCPFPDT.INPUT.CI_NO;
            BPRFSCH.FEE_STATUS = '0';
            CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
            T000_READ_BPTFSCH_3();
            if (pgmRtn) return;
            if (WS_FLAG == 'N') {
                BPRFSCH.FEE_STATUS = '1';
                CEP.TRC(SCCGWA, BPRFSCH.FEE_STATUS);
                T000_READ_BPTFSCH_3();
                if (pgmRtn) return;
            }
        } else if (WS_PROC_TYPE == 'P') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_NONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_FLAG == 'M') {
            WS_SCH_DTL_FLG = 'Y';
        }
    }
    public void C250_CTR_DTL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        WS_CTR_DTL_FLG = 'N';
        if (WS_PROC_TYPE == 'A') {
            BPRFCTR.CHARGE_AC = BPCPFPDT.INPUT.AC;
            T000_READ_BPTFCTR_1();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == 'D') {
            BPRFCTR.CARD_PSBK_NO = BPCPFPDT.INPUT.CARD_PSBK_NO;
            T000_READ_BPTFCTR_2();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == 'C') {
            BPRFCTR.CI_NO = BPCPFPDT.INPUT.CI_NO;
            T000_READ_BPTFCTR_3();
            if (pgmRtn) return;
        } else if (WS_PROC_TYPE == 'P') {
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_CI_CARD_NONE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_FLAG == 'M') {
            WS_CTR_DTL_FLG = 'Y';
        }
    }
    public void T000_READ_BPTFSCH_1() throws IOException,SQLException,Exception {
        null.fst = true;
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.col = "FEE_STATUS,CHARGE_AC";
        BPTFSCH_RD.where = "FEE_STATUS = :BPRFSCH.FEE_STATUS "
            + "AND CHARGE_AC = :BPRFSCH.CHARGE_AC";
        IBS.READ(SCCGWA, BPRFSCH, this, BPTFSCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAAAAAAAA");
            WS_FLAG = 'M';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BBBBBBBBB");
            WS_FLAG = 'N';
        } else {
        }
    }
    public void T000_READ_BPTFSCH_2() throws IOException,SQLException,Exception {
        null.fst = true;
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.col = "FEE_STATUS,CARD_PSBK_NO";
        BPTFSCH_RD.where = "FEE_STATUS = :BPRFSCH.FEE_STATUS "
            + "AND CARD_PSBK_NO = :BPRFSCH.CARD_PSBK_NO";
        IBS.READ(SCCGWA, BPRFSCH, this, BPTFSCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAAAAAAAA");
            WS_FLAG = 'M';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BBBBBBBBB");
            WS_FLAG = 'N';
        } else {
        }
    }
    public void T000_READ_BPTFSCH_3() throws IOException,SQLException,Exception {
        null.fst = true;
        BPTFSCH_RD = new DBParm();
        BPTFSCH_RD.TableName = "BPTFSCH";
        BPTFSCH_RD.col = "FEE_STATUS,CI_NO";
        BPTFSCH_RD.where = "FEE_STATUS = :BPRFSCH.FEE_STATUS "
            + "AND CI_NO = :BPRFSCH.CI_NO";
        IBS.READ(SCCGWA, BPRFSCH, this, BPTFSCH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAAAAAAAA");
            WS_FLAG = 'M';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BBBBBBBBB");
            WS_FLAG = 'N';
        } else {
        }
    }
    public void T000_READ_BPTFCTR_1() throws IOException,SQLException,Exception {
        null.fst = true;
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        BPTFCTR_RD.where = "FEE_STATUS = '0' "
            + "AND CHARGE_AC = :BPRFCTR.CHARGE_AC";
        IBS.READ(SCCGWA, BPRFCTR, this, BPTFCTR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAAAAAAAA");
            WS_FLAG = 'M';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BBBBBBBBB");
            WS_FLAG = 'N';
        } else {
        }
    }
    public void T000_READ_BPTFCTR_2() throws IOException,SQLException,Exception {
        null.fst = true;
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        BPTFCTR_RD.where = "FEE_STATUS = '0' "
            + "AND CARD_PSBK_NO = :BPRFCTR.CARD_PSBK_NO";
        IBS.READ(SCCGWA, BPRFCTR, this, BPTFCTR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAAAAAAAA");
            WS_FLAG = 'M';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BBBBBBBBB");
            WS_FLAG = 'N';
        } else {
        }
    }
    public void T000_READ_BPTFCTR_3() throws IOException,SQLException,Exception {
        null.fst = true;
        BPTFCTR_RD = new DBParm();
        BPTFCTR_RD.TableName = "BPTFCTR";
        BPTFCTR_RD.where = "FEE_STATUS = '0' "
            + "AND CI_NO = :BPRFCTR.CI_NO";
        IBS.READ(SCCGWA, BPRFCTR, this, BPTFCTR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "AAAAAAAAA");
            WS_FLAG = 'M';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "BBBBBBBBB");
            WS_FLAG = 'N';
        } else {
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DCZUCINF, DCCUCINF, true);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRFPDT() throws IOException,SQLException,Exception {
        if (BPCRFPDT.INFO.FDT_TYP == '0' 
            || BPCRFPDT.INFO.FDT_TYP == '1') {
            BPCRFPDT.INFO.POINTER = BPRFADT;
            BPCRFPDT.INFO.LEN = 558;
        } else {
            BPCRFPDT.INFO.POINTER = BPRFPDT;
            BPCRFPDT.INFO.LEN = 558;
        }
        IBS.CALLCPN(SCCGWA, CPN_R_FPDT, BPCRFPDT);
        if (BPCRFPDT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRFPDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPFPDT.RC);
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_DDCSCINM, DDCSCINM, true);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU         ", CICACCU);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        CEP.TRC(SCCGWA, BPCPFPDT.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPFPDT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPFPDT = ");
            CEP.TRC(SCCGWA, BPCPFPDT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
