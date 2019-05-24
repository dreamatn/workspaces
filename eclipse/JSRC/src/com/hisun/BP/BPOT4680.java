package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4680 {
    brParm BPTPREAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BPX80";
    int WS_I = 0;
    BPOT4680_WS_PREAC_DETAIL WS_PREAC_DETAIL = new BPOT4680_WS_PREAC_DETAIL();
    char WS_TBL_PREAC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPREAC BPRPREAC = new BPRPREAC();
    SCCGWA SCCGWA;
    BPB4680_AWA_4680 BPB4680_AWA_4680;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT4680 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4680_AWA_4680>");
        BPB4680_AWA_4680 = (BPB4680_AWA_4680) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BRW_BPTPREAC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BRW_BPTPREAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPREAC);
        if (BPB4680_AWA_4680.AC.trim().length() == 0 
                && BPB4680_AWA_4680.PRE_BR != 0) {
            T000_STARTBR_BY_PRE_BR();
            if (pgmRtn) return;
        } else if (BPB4680_AWA_4680.AC.trim().length() > 0) {
            T000_STARTBR_BY_AC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
        B010_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_I = 1; WS_TBL_PREAC_FLAG != '2' 
            && WS_I <= 500 
            && SCCMPAG.FUNC != 'E'; WS_I += 1) {
            IBS.init(SCCGWA, BPRPREAC);
            T000_READNEXT();
            if (pgmRtn) return;
            if (WS_TBL_PREAC_FLAG == '1') {
                B020_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, BPRPREAC);
        T000_ENDBR_BPTPREAC();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BY_PRE_BR() throws IOException,SQLException,Exception {
        if (BPB4680_AWA_4680.STS == ' ') {
            BPRPREAC.PRE_BR = BPB4680_AWA_4680.PRE_BR;
            BPTPREAC_BR.rp = new DBParm();
            BPTPREAC_BR.rp.TableName = "BPTPREAC";
            BPTPREAC_BR.rp.where = "PRE_BR = :BPRPREAC.PRE_BR";
            BPTPREAC_BR.rp.order = "AC , CUR_BR , PRE_BR";
            IBS.STARTBR(SCCGWA, BPRPREAC, this, BPTPREAC_BR);
        } else {
            BPRPREAC.PRE_BR = BPB4680_AWA_4680.PRE_BR;
            BPRPREAC.STS = BPB4680_AWA_4680.STS;
            BPTPREAC_BR.rp = new DBParm();
            BPTPREAC_BR.rp.TableName = "BPTPREAC";
            BPTPREAC_BR.rp.where = "STS = :BPRPREAC.STS "
                + "AND PRE_BR = :BPRPREAC.PRE_BR";
            BPTPREAC_BR.rp.order = "AC , CUR_BR , PRE_BR";
            IBS.STARTBR(SCCGWA, BPRPREAC, this, BPTPREAC_BR);
        }
    }
    public void T000_STARTBR_BY_AC() throws IOException,SQLException,Exception {
        if (BPB4680_AWA_4680.STS == ' ') {
            BPRPREAC.KEY.AC = BPB4680_AWA_4680.AC;
            BPTPREAC_BR.rp = new DBParm();
            BPTPREAC_BR.rp.TableName = "BPTPREAC";
            BPTPREAC_BR.rp.where = "AC = :BPRPREAC.KEY.AC";
            BPTPREAC_BR.rp.order = "AC , CUR_BR , PRE_BR";
            IBS.STARTBR(SCCGWA, BPRPREAC, this, BPTPREAC_BR);
        } else {
            BPRPREAC.KEY.AC = BPB4680_AWA_4680.AC;
            BPRPREAC.STS = BPB4680_AWA_4680.STS;
            BPTPREAC_BR.rp = new DBParm();
            BPTPREAC_BR.rp.TableName = "BPTPREAC";
            BPTPREAC_BR.rp.where = "STS = :BPRPREAC.STS "
                + "AND AC = :BPRPREAC.KEY.AC";
            BPTPREAC_BR.rp.order = "AC , CUR_BR , PRE_BR";
            IBS.STARTBR(SCCGWA, BPRPREAC, this, BPTPREAC_BR);
        }
    }
    public void T000_READNEXT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRPREAC, this, BPTPREAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_PREAC_FLAG = '1';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_PREAC_FLAG = '2';
        } else {
        }
    }
    public void T000_ENDBR_BPTPREAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTPREAC_BR);
    }
    public void B010_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 61;
        SCCMPAG.SCR_ROW_CNT = 99;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_PREAC_DETAIL);
        WS_PREAC_DETAIL.WS_AC = BPRPREAC.KEY.AC;
        WS_PREAC_DETAIL.WS_CUR_BR = BPRPREAC.CUR_BR;
        WS_PREAC_DETAIL.WS_PRE_BR = BPRPREAC.PRE_BR;
        WS_PREAC_DETAIL.WS_STS = BPRPREAC.STS;
        WS_PREAC_DETAIL.WS_LAST_UPD_DT = BPRPREAC.LAST_UPD_DT;
        WS_PREAC_DETAIL.WS_LAST_UPD_TLR = BPRPREAC.LAST_UPD_TLR;
        CEP.TRC(SCCGWA, WS_PREAC_DETAIL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_PREAC_DETAIL);
        SCCMPAG.DATA_LEN = 61;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PREAC_DETAIL);
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
